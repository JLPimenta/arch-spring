package com.absentapp.project.domain.core.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntityResponse {
    private String id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}