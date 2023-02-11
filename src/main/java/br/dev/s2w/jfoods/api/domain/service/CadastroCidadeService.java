package br.dev.s2w.jfoods.api.domain.service;

import br.dev.s2w.jfoods.api.domain.exception.EntidadeEmUsoException;
import br.dev.s2w.jfoods.api.domain.exception.EntidadeNaoEncontradaException;
import br.dev.s2w.jfoods.api.domain.model.Cidade;
import br.dev.s2w.jfoods.api.domain.model.Estado;
import br.dev.s2w.jfoods.api.domain.repository.CidadeRepository;
import br.dev.s2w.jfoods.api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("O cadastro de estado com ID %d não foi encontrado", estadoId)
                ));

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("O cadastro de cidade com ID %d não foi encontrado", cidadeId)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("A cidade com ID %d não pode ser removida porque está em uso", cidadeId)
            );
        }
    }
}
