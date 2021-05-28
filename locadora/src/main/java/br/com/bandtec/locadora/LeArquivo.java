package br.com.bandtec.locadora;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeArquivo {

    public static void leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nome, diretor, sinopse;
        Integer anoLancamento, genero, contRegistro=0;;
        Boolean nacional;
        Double precoLocacao;

        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                if (tipoRegistro.equals("00")) {
                    System.out.println("Header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 17));
                    System.out.println("Data/hora de geração do arquivo: " + registro.substring(17,33));
                    System.out.println("Versão do layout: " + registro.substring(33,37));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("\nTrailer");
                    int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                    if (qtdRegistro == contRegistro) {
                        System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                    }
                    else {
                        System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    if (contRegistro == 0) {
                        System.out.println();
                        System.out.printf("%-50s %-50s %4s %-5s %5s %10s %-300s\n", "FILME","DIRETOR","ANO","NAC",
                                "GENERO", "PRECO LOCACAO", "SINOPSE");

                    }

                    nome = registro.substring(2,52);
                    diretor = registro.substring(52,102);
                    anoLancamento = Integer.parseInt(registro.substring(102,106));
                    nacional = Boolean.parseBoolean(registro.substring(106,112));
                    genero = Integer.parseInt(registro.substring(112,142));
                    precoLocacao = Double.parseDouble(registro.substring(142,172).replace(',','.'));
                    sinopse = registro.substring(172,472);

                    System.out.printf("%-50s %-50s %4d %-5s %5d %10.2f %300s\n", nome, diretor, anoLancamento, nacional,
                            genero, precoLocacao, sinopse);
                    contRegistro++;
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

    }

    public static void main(String[] args) {
        String nomeArq = "filmes.txt";
        leArquivo(nomeArq);
    }

}
