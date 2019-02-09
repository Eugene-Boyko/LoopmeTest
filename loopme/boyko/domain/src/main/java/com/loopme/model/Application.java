package com.loopme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Application implements IEntityBO {

    @EqualsAndHashCode.Include
    private String name;

    @EqualsAndHashCode.Include
    private ApplicationType applicationType;

    private List<ContentType> contentTypes;

    private User user;

    @Override
    public String getBusinessKey() {
        return name;
    }
}
