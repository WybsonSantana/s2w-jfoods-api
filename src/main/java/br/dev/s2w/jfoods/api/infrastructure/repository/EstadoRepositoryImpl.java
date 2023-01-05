package br.dev.s2w.jfoods.api.infrastructure.repository;

import br.dev.s2w.jfoods.api.domain.model.Estado;
import br.dev.s2w.jfoods.api.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        manager.remove(estado);
    }

}
