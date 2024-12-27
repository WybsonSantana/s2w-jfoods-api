package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}