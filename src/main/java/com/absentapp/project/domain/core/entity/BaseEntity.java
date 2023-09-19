package com.absentapp.project.domain.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "DATA_CRIACAO", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ATUALIZACAO", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAtualizacao;

    public abstract String getId();

    public abstract void setId(String string);

    @PrePersist
    protected void prePersist() {
        if (isNull(this.getId()) || StringUtils.isBlank(this.getId())) {
            this.setId(UUID.randomUUID().toString());
        }
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
