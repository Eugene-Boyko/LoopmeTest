package com.loopme.dbmodel;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="application")
public class Application implements IDaoModel {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @NaturalId
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ApplicationType applicationType;

    @ElementCollection(targetClass = ContentType.class)
    @CollectionTable(name = "application_ContentTypes",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "contentTypeId")
    private List<ContentType> contentTypes;

    @Column(insertable = false, updatable = false)
    private String userId;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @Override
    public Long getId() {
        return id;
    }
}
