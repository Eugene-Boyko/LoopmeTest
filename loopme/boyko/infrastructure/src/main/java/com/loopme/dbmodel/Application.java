package com.loopme.dbmodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="application")
public class Application implements IDaoEntity {

    private static final long serialVersionUID = 822353117270863796L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @EqualsAndHashCode.Include
    @NaturalId(mutable = true)
    @Column(nullable = false)
    private String name;

    @EqualsAndHashCode.Include
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

