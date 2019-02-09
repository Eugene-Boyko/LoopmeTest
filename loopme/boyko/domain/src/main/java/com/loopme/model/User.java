package com.loopme.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements IEntityBO {

    private String name;

    private String email;

    private UserRole role;

    @Override
    public String getBusinessKey() {
        return name;
    }
}
