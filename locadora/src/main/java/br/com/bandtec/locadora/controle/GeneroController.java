package br.com.bandtec.locadora.controle;

import br.com.bandtec.locadora.dominio.Genero;
import br.com.bandtec.locadora.modelo.PilhaObj;
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

    PilhaObj<Genero> pilha = new PilhaObj<>(10);

    @PostMapping
    public ResponseEntity postGenero(@RequestBody @Valid Genero novoGenero) {

        pilha.push(novoGenero);
        repository.save(novoGenero);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getGeneros() {

        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();

        } else {
            return ResponseEntity.status(201).body(repository.findAll());
        }
    }

    @DeleteMapping("/{idGenero}")
    public ResponseEntity deleteGenero(@PathVariable Integer idGenero) {

        if(repository.existsById(idGenero)) {
            repository.deleteById(idGenero);
            return ResponseEntity.status(200).build();

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // desfazer ultimo post realizado

    @DeleteMapping("/desfazer-post")
    public ResponseEntity desfazerPostGenero() {

        if(pilha.isEmpty()) {
            String message = "Não foi possível desfazer POST, não existem generos.";
            return ResponseEntity.status(204).body(message);

        } else {
            repository.delete(pilha.pop());
            return ResponseEntity.status(200).build();
        }
    }


}
