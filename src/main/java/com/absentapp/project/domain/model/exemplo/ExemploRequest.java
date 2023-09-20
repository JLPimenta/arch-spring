package com.absentapp.project.domain.model.exemplo;


import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExemploRequest {

    private String descricao;

    @Builder.Default
    private Boolean ativo = Boolean.TRUE;
}
