package br.com.bandtec.locadora.dominio;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String nome;

    @NotBlank
    @Size(min = 2, max = 50)
    private String diretor;

    @PositiveOrZero
    private Integer anoLancamento;

    private Boolean nacional;

    @ManyToOne
    @NotNull
    private Genero genero;

    @NotNull
    @PositiveOrZero
    private Integer qtdDiasLocacao;

    @NotNull
    @PositiveOrZero
    private Double precoLocacao;

    @Size(max = 300)
    private String sinopse;


    public Double calcularPrecoLocacao() {

        return qtdDiasLocacao * precoLocacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public Boolean getNacional() {
        return nacional;
    }

    public void setNacional(Boolean nacional) {
        this.nacional = nacional;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getQtdDiasLocacao() {
        return qtdDiasLocacao;
    }

    public void setQtdDiasLocacao(Integer qtdDiasLocacao) {
        this.qtdDiasLocacao = qtdDiasLocacao;
    }

    public Double getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(Double precoLocacao) {
        this.precoLocacao = precoLocacao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
}
