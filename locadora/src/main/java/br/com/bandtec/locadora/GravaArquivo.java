package br.com.bandtec.locadora;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GravaArquivo {

    public static void gravaRegistro (String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }



    public static void main(String[] args) {

        String nomeArq = "filmes.txt";
        String header = "";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        // Monta o registro header
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00FILMES-REGISTRO";
        header += formatter.format(dataDeHoje);
        header += "01";

        gravaRegistro(nomeArq, header);


        corpo += "02";
        corpo += String.format("%-50s", "ROCKY, um lutador");
        corpo += String.format("%-50s", "Silvester Stallone");
        corpo += String.format("%04d", 1997);
        corpo += String.format("%-5s", "false");
        corpo += String.format("%05d", 2);
        corpo += String.format("%010.2f", 2.3);
        corpo += String.format("%-300s", "Rocky Balboa, um pequeno boxeador da classe trabalhadora da Filadélfia, é arbitrariamente escolhido para lutar contra o campeão dos pesos pesados, Apollo Creed.");

        contRegDados++;
        gravaRegistro(nomeArq,corpo);

        corpo = "02";
        corpo += String.format("%-50s", "A fada do repolho");
        corpo += String.format("%-50s", "Alice Guy");
        corpo += String.format("%04d", 1900);
        corpo += String.format("%-5s", "false");
        corpo += String.format("%05d", 2);
        corpo += String.format("%010.2f", 0.2);
        corpo += String.format("%-300s", "Ele é baseado em uma antiga lenda francesa, segundo a qual bebês meninos nascem de repolhos, enquanto as meninas nasceriam de rosas.");

        contRegDados++;
        gravaRegistro(nomeArq,corpo);

        // 3o registro de dados
        corpo = "02";
        corpo += String.format("%-50s", "Movo avião");
        corpo += String.format("%-50s", "Cesar Rodrigues");
        corpo += String.format("%04d", 2020);
        corpo += String.format("%-5s", "true");
        corpo += String.format("%05d", 2);
        corpo += String.format("%010.2f", 1.2);
        corpo += String.format("%-300s", "Ana recebe uma proposta para ser influenciadora digital de uma marca renomada. Ela larga a faculdade para investir todo o seu tempo na página digital criando publicações, mas o trabalho que a princípio seria incrível, foi ficando cada vez mais nocivo para ela mesma. ");

        contRegDados++;
        gravaRegistro(nomeArq,corpo);


        // monta o trailer
        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq,trailer);
    }

}
