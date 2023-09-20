package com.absentapp.project.domain.core.controller;

import com.absentapp.project.domain.core.entity.BaseEntity;
import com.absentapp.project.domain.core.exception.DomainException;
import com.absentapp.project.domain.core.mapper.IBaseMapper;
import com.absentapp.project.domain.core.service.IBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 *
 * T ⇾ Entidade
 * R ⇾ Classe de requisição
 * Q ⇾ Classe de Resposta
 *
 * */

public abstract class BaseController<T extends BaseEntity, R, Q> {
    private final IBaseMapper<T, R, Q> mapper;
    private final IBaseService<T> service;

    protected BaseController(final IBaseMapper<T, R, Q> mapper, final IBaseService<T> service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    @Transactional
    @Operation(description = "Cria um novo registro.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Erro ao realizar criação do novo registro")
    })
    public Q create(@Valid @RequestBody R entityRequest) throws DomainException {
        return service.create(mapper.fromRequest(entityRequest), mapper::toResponse);
    }
}
