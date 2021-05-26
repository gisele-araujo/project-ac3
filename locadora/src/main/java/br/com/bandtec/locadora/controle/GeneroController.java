package br.com.bandtec.locadora.controle;

import br.com.bandtec.locadora.dominio.Genero;
import br.com.bandtec.locadora.repositorio.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository repository;

    @PostMapping
    public ResponseEntity postGenero(@RequestBody @Valid Genero novoGenero) {

        repository.save(novoGenero);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getGeneros() {

        return ResponseEntity.status(201).body(repository.findAll());
    }
}
