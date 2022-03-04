package hellojpa.domain;

import hellojpa.domain.item.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DDL 생성 기능은 DDL을 자동 생성할 때만 사용되고 JPA의 실행 로직에는 영향을 주지 않는다.
 *
 *
 * //@Table(name = "MBR")
 * //@Table(uniqueConstraints = {@UniqueConstraint(name = "NAME_AGE_UNIQUE",
 * //        columnNames = {"NAME", "AGE"})})
 * //@SequenceGenerator(
 * //        name = "member_seq_generator",
 * //        sequenceName = "member_seq",
 * //        initialValue = 1, allocationSize = 1    // 기본값이 50이므로 시퀀스 값이 1씩 증가하면 1로 설정해야한다.
 * //)
 */
@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();



//    @ManyToMany
//    @JoinTable(name = "member_product")
//    private List<Product> products = new ArrayList<>();

//    /* 연관관계 편의 메소드 */
//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }

//    @Column(name = "team_id")
//    private Long teamId;

/*
    @Id // 직접 할당 시에는 @Id 만 사용
    @GeneratedValue(strategy = GenerationType.AUTO)    // 방언에 따라 자동 지정, 기본값
    // GenerationType.IDENTITY -> 데이터베이스에 위임, MySQL
    // GenerationType.SEQUENCE -> 데이터베이스 시퀀스 오브젝트 사용, ORACLE -> @SequenceGenerator 필요
    // GenerationType.TABLE -> 키 생성용 테이블 사용, 모든 DB에서 사용 -> @TableGenerator 필요
    private Long id;

    // DDL 생성 기능
    @Column(name = "name",  // 필드와 매핑할 테이블의 컬럼 이름
            nullable = false, // null 값의 허용 여부 false -> DDL 에 not null 제약조건 추가
            length = 10 // 길이 제약조건, String 타입에만 사용한다.
//            insertable = false, updatable = false,  // 등록, 변경 가능 여부
//            unique = true   // 유니크 제약 조건을 걸때 사용하나 주로 @Table 에서 사용한다.
//            columnDefinition = "varchar(100) default 'EMPTY'" // 데이터베이스 컬럼 정보를 직접 줄 수 있다.
    )
    private String name;

    private Integer age;

    // 기본값 EnumType.ORDINAL -> enum 순서를 데이터베이스에 저장하므로 사용X
    // -> 중간에 enum의 순서가 변경되면 데이터베이스에 데이터가 꼬인다.
    // EnumType.STRING -> enum 이름을 데이터베이스에 저장
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP) // LocalDate, LocalDateTime 을 사용할 때는 생략 가능
    private LocalDateTime createdDate;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastModifiedDate;

    @Lob    // 문자열이면 자동으로 CLOB 매핑 나머지는 BLOB
    private String description;

    @Transient  // 특정 필드를 컬럼에 매핑하지 않음
    private int temp;
*/
}
