package com.absentapp.project.domain.model.exemplo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.BooleanFlag;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExemploRequest {
    @Max(100)
    private String descricao;

    @BooleanFlag()
    @NotBlank()
    private Boolean ativo;
}
