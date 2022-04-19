package jpabook.jpashop.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@EqualsAndHashCode
public class Address {
    @Column(length = 10)
    private String city;
    @Column(length = 20)
    private String street;
    @Column(length = 5)
    private String zipcode;

    public String getFullAddress() {
        return getCity() + ", " + getStreet() + ", " + getZipcode();
    }
}
