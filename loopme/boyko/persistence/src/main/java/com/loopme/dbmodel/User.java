package com.loopme.dbmodel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="user")
public class User implements IDaoModel {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @NaturalId
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
