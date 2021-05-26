package br.com.bandtec.locadora.dominio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomeCliente;

    @OneToMany
    private List<Filme> filmes;

    private double precoTotal;


    private double precoTotalCarrinho() {

        double total = 0.00;

        /* for (Filme filme : filmes) {
            total = filme.calcularPrecoLocacao();
        } */

        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

   /* public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
*/
    public double getPrecoTotal() {
        return precoTotalCarrinho();
    }
}
