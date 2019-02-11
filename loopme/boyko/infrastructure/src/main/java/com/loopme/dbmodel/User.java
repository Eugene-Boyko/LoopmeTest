package com.loopme.dbmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "User")
@NamedQueries({@NamedQuery(name = User.USERS_BY_USER_ROLE_QUERY, query = "from User u where u.role = :role")})
public class User implements IDaoEntity {

    public static final String USERS_BY_USER_ROLE_QUERY = "USERS_BY_USER_ROLE_QUERY";

    private static final long serialVersionUID = 6917335361960599156L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NaturalId(mutable = true)
    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
