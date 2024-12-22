package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {
    List<Permission> list();

    Permission search(Long id);

    Permission save(Permission permission);

    void remove(Permission permission);
}
