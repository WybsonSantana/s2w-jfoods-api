package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}