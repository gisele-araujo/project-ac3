package br.com.bandtec.locadora.repositorio;

import br.com.bandtec.locadora.dominio.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

}
