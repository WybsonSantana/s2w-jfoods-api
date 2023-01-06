package br.dev.s2w.jfoods.api.gateway.controller;

import br.dev.s2w.jfoods.api.domain.model.Cozinha;
import br.dev.s2w.jfoods.api.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> listarJson() {
        System.out.println("listarJson()");
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
public List<Cozinha> listarXml() {
        System.out.println("listarXml()");
        return cozinhaRepository.listar();
    }

}
