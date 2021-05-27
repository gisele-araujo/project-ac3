package br.com.bandtec.locadora.controle;

import br.com.bandtec.locadora.dominio.Filme;
import br.com.bandtec.locadora.modelo.FilaObj;
import br.com.bandtec.locadora.modelo.PilhaObj;
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

    PilhaObj<Filme> pilha = new PilhaObj<>(10);


    @GetMapping
    public ResponseEntity getFilmes() {

        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();

        } else {
            return ResponseEntity.status(200).body(repository.findAll());
        }
    }

    @GetMapping("/genero/{idGenero}")
    public ResponseEntity getFilmesPorGenero(@PathVariable int idGenero) {

        if(repository.existsById(idGenero)) {
            return ResponseEntity.status(200).body(repository.findByGeneroId(idGenero));

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/precos")
    public ResponseEntity getPrecosFilmes() {

        if(repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();

        } else {
            return ResponseEntity.status(200).body(repository.findAllFilmePreco());
        }
    }

    @PostMapping
    public ResponseEntity postFilme(@RequestBody @Valid Filme novoFilme) {

        repository.save(novoFilme);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{idFilme}")
    public ResponseEntity putFilme(@PathVariable Integer idFilme,
                                   @RequestBody @Valid Filme atualizarFilme) {

        pilha.push(repository.findById(idFilme).get());

        if (repository.existsById(idFilme)) {
            atualizarFilme.setId(idFilme);
            repository.save(atualizarFilme);

            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("{idFilme}")
    public ResponseEntity deleteFilme(@PathVariable Integer idFilme) {

        if(repository.existsById(idFilme)) {
            repository.deleteById(idFilme);
            return ResponseEntity.status(200).build();

        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // desfazer ultimo update de filmes

    @DeleteMapping("/desfazer-update")
    public ResponseEntity desfazerPutFilme() {

        if(pilha.isEmpty()) {
            String message = "Não foi possível desfazer POST, não existem generos.";
            return  ResponseEntity.status(204).body(message);

        } else {
            repository.save(pilha.pop());
            return ResponseEntity.status(200).body(pilha.pop());
        }
    }
}
