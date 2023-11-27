# Documentação de Código

**Pacote**: `com.scalesec.vulnado`

Este pacote contém classes e métodos que lidam com operações de extração de links de uma URL específica.

## Importações

```java
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.*;
```

Essas importações incluem classes necessárias para conectar-se a uma URL, extrair e manipular elementos HTML da página da web e lidar com exceções de entrada e saída.

## Classe: `LinkLister`

Esta é a classe principal que contém os métodos para listar links.

### Método: `getLinks(String url)`

```java
public static List<String> getLinks(String url) throws IOException {
  List<String> result = new ArrayList<String>();
  Document doc = Jsoup.connect(url).get();
  Elements links = doc.select("a");
  for (Element link : links) {
    result.add(link.absUrl("href"));
  }
  return result;
}
```

Este método recebe uma URL como entrada e retorna uma lista de links absolutos (`href`) encontrados na página. Ele faz isso conectando-se à URL, selecionando todos os elementos `a` (links) na página e adicionando suas URLs absolutas à lista de resultados.

### Método: `getLinksV2(String url)`

```java
public static List<String> getLinksV2(String url) throws BadRequest {
  try {
    URL aUrl= new URL(url);
    String host = aUrl.getHost();
    System.out.println(host);
    if (host.startsWith("172.") || host.startsWith("192.168") || host.startsWith("10.")){
      throw new BadRequest("Use of Private IP");
    } else {
      return getLinks(url);
    }
  } catch(Exception e) {
    throw new BadRequest(e.getMessage());
  }
}
```

Este método aprimorado também recebe uma URL como entrada e retorna uma lista de links encontrados na página. No entanto, antes de fazer isso, verifica se o host da URL começa com "172.", "192.168" ou "10.". Se começar, uma exceção `BadRequest` é lançada, pois esses são intervalos de endereços IP privados. Se o host não começar com esses prefixos, o método simplesmente chama `getLinks(url)` para obter os links. Se ocorrer qualquer outra exceção durante a execução do método, também será lançada uma exceção `BadRequest` com a mensagem da exceção original.