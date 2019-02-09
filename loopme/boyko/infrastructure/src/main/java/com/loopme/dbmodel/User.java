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
@Table(name="user")
public class User implements IDaoEntity {

    private static final long serialVersionUID = 6917335361960599156L;

    @Id
    @GeneratedValue
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

    @Override
    public Long getId() {
        return id;
    }
}
