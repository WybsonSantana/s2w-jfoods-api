package br.dev.s2w.jfoods.api.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Address {
    @Column(name = "address_postal_code")
    private String postalCode;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_complement")
    private String complement;

    @Column(name = "address_district")
    private String district;

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;
}
