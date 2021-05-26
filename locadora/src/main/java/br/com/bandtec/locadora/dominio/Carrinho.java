package br.com.bandtec.locadora.dominio;

import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 5, max = 40)
    private String nomeCliente;

    @OneToMany
    private List<Filme> filmes;

    @PositiveOrZero
    @ReadOnlyProperty
    private Double precoTotal;


    private Double precoTotalCarrinho() {

        double total = 0.00;

        for (Filme filme : filmes) {
            total = filme.calcularPrecoLocacao();
        }

        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

   public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public Double getPrecoTotal() {
        return precoTotalCarrinho();
    }
}
