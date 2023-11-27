package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger; // Incluido por GFT AI Impact Bot

public class Cowsay {
  private static final Logger logger = Logger.getLogger(Cowsay.class.getName()); // Incluido por GFT AI Impact Bot

  private Cowsay() { // Incluido por GFT AI Impact Bot
    // Construtor privado para ocultar o público implícito
  }

  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    // Certifique-se de que este argumento de comando controlado pelo usuário não leve a um comportamento indesejado
    String sanitizedInput = sanitizeInput(input); // Incluido por GFT AI Impact Bot
    String cmd = "/usr/games/cowsay '" + sanitizedInput + "'"; // Alterado por GFT AI Impact Bot
    logger.info(cmd); // Alterado por GFT AI Impact Bot

    // Certifique-se de que o "PATH" usado para encontrar este comando inclui apenas o que você pretende
    processBuilder.command("/bin/bash", "-c", cmd); // Alterado por GFT AI Impact Bot

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      logger.severe(e.getMessage()); // Alterado por GFT AI Impact Bot
    }
    return output.toString();
  }

  // Método para sanitizar a entrada do usuário
  private static String sanitizeInput(String input) { // Incluido por GFT AI Impact Bot
    return input.replaceAll("[^a-zA-Z0-9 ]", ""); // Incluido por GFT AI Impact Bot
  }
}