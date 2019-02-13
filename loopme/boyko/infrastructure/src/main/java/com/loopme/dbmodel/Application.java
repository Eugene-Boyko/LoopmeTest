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
@Table(name = "Application")
@NamedQueries({@NamedQuery(name = Application.APPLICATIONS_BY_PUBLISHER_USER_NAME_QUERY, query =
        "select app from Application app join User u on app.userId = u.id where u.name = :name and u.role = 'PUBLISHER'")})
public class Application implements IDaoEntity {

    public static final String APPLICATIONS_BY_PUBLISHER_USER_NAME_QUERY = "APPLICATIONS_BY_PUBLISHER_USER_NAME_QUERY";

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

    @ElementCollection(targetClass = ContentType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "application_ContentTypes",
            joinColumns = @JoinColumn(name = "id"))
    @Column(name = "contentTypeId")
    private List<ContentType> contentTypes;

    @Column(insertable = false, updatable = false)
    private String userId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}

