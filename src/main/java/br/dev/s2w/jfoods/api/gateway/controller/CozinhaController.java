package br.dev.s2w.jfoods.api.gateway.controller;

import br.dev.s2w.jfoods.api.domain.exception.EntidadeEmUsoException;
import br.dev.s2w.jfoods.api.domain.exception.EntidadeNaoEncontradaException;
import br.dev.s2w.jfoods.api.domain.model.Cozinha;
import br.dev.s2w.jfoods.api.domain.repository.CozinhaRepository;
import br.dev.s2w.jfoods.api.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        }

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        try {
            if (cozinha.getNome() != "") {
                return ResponseEntity.ok(cadastroCozinha.salvar(cozinha));
            }

            return ResponseEntity.badRequest().build();
        } catch (PersistenceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        try {
            Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

            if (cozinhaAtual != null && cozinha.getNome() != "") {
                BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

                cozinhaAtual = cozinhaRepository.salvar(cozinhaAtual);

                return ResponseEntity.ok(cozinhaAtual);
            } else if (cozinha.getNome() == "") {
                return ResponseEntity.badRequest().build();
            }

            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
