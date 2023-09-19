package com.absentapp.project.domain.entity;

import com.absentapp.project.domain.core.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXEMPLO")
public class Exemplo extends BaseEntity {

    @Id
    @Column(name = "ID_EXEMPLO", nullable = false, length = 36)
    private String id;

    @Column(name = "DESCRICAO", length = 100)
    private String descricao;

    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo;
}
