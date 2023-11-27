# Documentação de Código Terraform

O código a seguir é escrito em Terraform, que é uma ferramenta de Infraestrutura como Código (IaC) usada para fornecer infraestrutura de TI usando uma linguagem de programação de alto nível.

## Provedor AWS

```hcl
provider "aws"  {
  region = "${var.region}"
}
```

Esta parte do código configura o provedor AWS para o Terraform. A região AWS é fornecida como uma variável.

## Recurso VPC

```hcl
resource "aws_vpc" "main" {
  cidr_block = "${var.vpc_cidr}"
  tags = {
    Name = "tmp_vulnado_rev_shell_vpc"
  }
}
```

Aqui, um VPC (Virtual Private Cloud) é criado com um bloco CIDR fornecido como uma variável. 

## Internet Gateway

```hcl
resource "aws_internet_gateway" "gw" {
  vpc_id = "${aws_vpc.main.id}"
  tags = {
    Name = "tmp_vulnado_rev_shell_igw"
  }
}
```

Este bloco cria um Internet Gateway e o associa ao VPC criado anteriormente.

## Tabela de Rotas

```hcl
resource "aws_route_table" "r" {
  vpc_id = "${aws_vpc.main.id}"
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.gw.id}"
  }
  tags = {
    Name = "tmp_vulnado_rev_shell_rt"
  }
}
```

Este bloco cria uma tabela de rotas para o VPC e adiciona uma rota para o gateway da Internet.

## Subnet

```hcl
resource "aws_subnet" "subnet" {
  vpc_id     = "${aws_vpc.main.id}"
  cidr_block = "${var.subnet_cidr}"
  availability_zone = "${var.region}b"
  map_public_ip_on_launch = true
  tags = {
    Name = "tmp_vulnado_rev_shell_subnet"
  }
}
```

Este bloco cria uma subnet no VPC. O bloco CIDR para a subnet é fornecido como uma variável.

## Associação de Tabela de Rotas

```hcl
resource "aws_route_table_association" "assoc" {
  subnet_id      = "${aws_subnet.subnet.id}"
  route_table_id = "${aws_route_table.r.id}"
}
```

Este bloco associa a tabela de rotas ao subnet.

## Grupo de Segurança

```hcl
resource "aws_security_group" "sg" {
  name        = "tmp_vulnado_rev_shell_sg"
  vpc_id      = "${aws_vpc.main.id}"
  ingress {
    protocol    = "tcp"
    from_port   = 22
    to_port     = 22
    cidr_blocks = ["0.0.0.0/0"]
  }
  ingress {
    protocol    = "tcp"
    from_port   = 443
    to_port     = 443
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port = 0
    to_port   = 0
    protocol  = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
```

Este bloco cria um grupo de segurança para o VPC com regras de entrada e saída.

## AMI

```hcl
data "aws_ami" "amznlinux" {
  most_recent = true
  owners = ["amazon"]
  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-2.0.20181114-x86_64-gp2"]
  }
}
```

Este bloco recupera a AMI (Amazon Machine Image) mais recente para o Amazon Linux.

## Chave de Acesso

```hcl
resource "aws_key_pair" "attacker" {
  key_name   = "tmp-vulnado-deploy-key"
  public_key = "${var.public_key}"
}
```

Este bloco cria um par de chaves para acesso à instância EC2.

## Instância EC2

```hcl
resource "aws_instance" "receiver" {
  ami           = "${data.aws_ami.amznlinux.id}"
  instance_type = "t2.micro"
  subnet_id = "${aws_subnet.subnet.id}"
  key_name = "${aws_key_pair.attacker.key_name}"
  vpc_security_group_ids = ["${aws_security_group.sg.id}"]
  user_data = <<EOF
#!/bin/bash
yum update
yum install -y nmap
EOF
  tags = {
    Name = "VulnadoReverseShellReceiver"
  }
}
```

Por fim, este bloco cria uma instância EC2 na subnet com a AMI recuperada e o par de chaves criados anteriormente. Ele também executa um script de inicialização para atualizar o sistema e instalar o nmap.