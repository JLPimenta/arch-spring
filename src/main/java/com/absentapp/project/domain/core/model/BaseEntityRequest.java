package com.absentapp.project.domain.core.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityRequest {
    private String id;

    public static BaseEntityRequest of(String id) {
        return new BaseEntityRequest(id);
    }
}
