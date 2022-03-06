package hellojpa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "address")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressEntity {

    @Id @GeneratedValue
    private Long id;
    private Address address;

    public AddressEntity(Address address) {
        this.address = address;
    }
}
