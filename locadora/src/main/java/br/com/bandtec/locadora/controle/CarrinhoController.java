package br.com.bandtec.locadora.controle;

import br.com.bandtec.locadora.dominio.Carrinho;
import br.com.bandtec.locadora.dominio.Filme;
import br.com.bandtec.locadora.repositorio.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoRepository repository;

    @GetMapping
    public ResponseEntity getCarrinhos() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @GetMapping("/{idCarrinho}")
    public ResponseEntity getCarrinho(@PathVariable int idCarrinho) {

        return ResponseEntity.status(200).body(repository.findById(idCarrinho));

    }

    @PostMapping("/{idCarrinho}")
    public ResponseEntity alugarFilme(@PathVariable int idCarrinho,
                                      @RequestBody @Valid Filme filmeAlugado) {

        return ResponseEntity.status(201).build();

    }

    @DeleteMapping
    public ResponseEntity desfazerPostAlugarFilme() {

        return ResponseEntity.status(200).build();
    }
}
