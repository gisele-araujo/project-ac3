package br.com.bandtec.locadora.respostas;

import br.com.bandtec.locadora.dominio.Filme;

public class FilmePrecoResposta {

    private String nome;
    private double precoLocacao;

    public FilmePrecoResposta(Filme filme) {
        this.nome = filme.getNome();
        this.precoLocacao = filme.getPrecoLocacao();
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoLocacao() {
        return precoLocacao;
    }
}
