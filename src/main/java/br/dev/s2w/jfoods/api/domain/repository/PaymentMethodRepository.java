package br.dev.s2w.jfoods.api.domain.repository;

import br.dev.s2w.jfoods.api.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {
    List<PaymentMethod> list();

    PaymentMethod search(Long id);

    PaymentMethod save(PaymentMethod paymentMethod);

    void remove(PaymentMethod paymentMethod);
}
