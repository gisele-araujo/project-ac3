package br.com.bandtec.locadora.controle;

import br.com.bandtec.locadora.dominio.Filme;
import br.com.bandtec.locadora.repositorio.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository repository;

    @PostMapping
    public ResponseEntity postFilme(@RequestBody @Valid Filme novoFilme) {

        repository.save(novoFilme);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getFilmes() {

        return ResponseEntity.status(200).body(repository.findAll());
    }

    @GetMapping("/genero/{idGenero}")
    public ResponseEntity getFilmesPorGenero(@PathVariable int idGenero) {
        return ResponseEntity.status(200).body(repository.pesquisarPorGenero(idGenero));

    }


}
