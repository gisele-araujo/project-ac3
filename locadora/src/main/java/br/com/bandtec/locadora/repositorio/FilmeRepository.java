package br.com.bandtec.locadora.repositorio;

import br.com.bandtec.locadora.dominio.Filme;
import br.com.bandtec.locadora.respostas.FilmePrecoResposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {


    List<Filme> findByGeneroId(int idGenero);

    @Query("select f from Filme f")
    List<FilmePrecoResposta> findAllFilmePreco();

}
