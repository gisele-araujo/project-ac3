package br.com.bandtec.locadora.controle;

import br.com.bandtec.locadora.dominio.Carrinho;
import br.com.bandtec.locadora.dominio.Filme;
import br.com.bandtec.locadora.modelo.PilhaObj;
import br.com.bandtec.locadora.repositorio.CarrinhoRepository;
import br.com.bandtec.locadora.repositorio.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository repository;

    @Autowired
    private FilmeRepository filmes;

    PilhaObj<Filme> pilhaFilmes = new PilhaObj<>(10);


    @GetMapping
    public ResponseEntity getCarrinhos() {

        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();

        } else {
            return ResponseEntity.status(200).body(repository.findAll());
        }
    }

    @GetMapping("/{idCarrinho}")
    public ResponseEntity getCarrinho(@PathVariable Integer idCarrinho) {

        if(repository.existsById(idCarrinho)) {
            return ResponseEntity.status(200).body(repository.findById(idCarrinho));

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity postCadastrarNovoCarrinho(@RequestBody @Valid Carrinho nomeCliente) {

        repository.save(nomeCliente);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{idCarrinho}/{idFilme}")
    public ResponseEntity alugarFilme(@PathVariable Integer idCarrinho,
                                      @PathVariable Integer idFilme) {

        if(repository.existsById(idCarrinho) && filmes.existsById(idFilme) ) {

            Carrinho clienteCarrinho = repository.findById(idCarrinho).get();
            List<Filme> listaFilmeCliente = clienteCarrinho.getFilmes();
            Filme filmeEscolhido = filmes.findById(idFilme).get();

            listaFilmeCliente.add(filmeEscolhido);
            pilhaFilmes.push(filmeEscolhido);
            repository.save(clienteCarrinho);


            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    // desfazer post de filme alugado de um determinado carrinho

    @DeleteMapping("/desfazer-post/{idCarrinho}")
    public ResponseEntity desfazerAlugarFilme(@PathVariable Integer idCarrinho) {

        Carrinho carrinhoAlvo = repository.findById(idCarrinho).get();

        if(pilhaFilmes.isEmpty()) {
            return ResponseEntity.status(204).body("Não foi possível desfazer POST, não existem filmes alugados em sua lista.");
        } else {
            carrinhoAlvo.getFilmes().remove((pilhaFilmes.pop().getId()));
            repository.save(carrinhoAlvo);
            return ResponseEntity.status(200).build();
        }
    }
}
