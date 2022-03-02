package hellojpa;

import javax.persistence.*;

/**
 * DDL 생성 기능은 DDL을 자동 생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다.
 */
@Entity
//@Table(name = "MBR")
//@Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE",
//        columnNames = {"NAME", "AGE"})})
public class Member {

    @Id
    private Long id;

    // DDL 생성 기능
    @Column(nullable = false, length = 10)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
