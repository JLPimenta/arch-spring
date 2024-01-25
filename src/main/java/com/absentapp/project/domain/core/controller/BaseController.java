package com.absentapp.project.domain.core.controller;

import com.absentapp.project.domain.core.entity.BaseEntity;
import com.absentapp.project.domain.core.exception.DomainException;
import com.absentapp.project.domain.core.mapper.IBaseMapper;
import com.absentapp.project.domain.core.service.IBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * T ⇾ Entidade
 * R ⇾ Classe de requisição
 * Q ⇾ Classe de Resposta
 */

@Getter
public abstract class BaseController<T extends BaseEntity, R, Q> extends BaseSearchController<T, R, Q> {
    private final IBaseMapper<T, R, Q> mapper;
    private final IBaseService<T> service;

    protected BaseController(final IBaseMapper<T, R, Q> mapper, final IBaseService<T> service) {
        super(service, mapper);
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    @Transactional
    @Operation(description = "Cria um novo registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar criação do novo registro.")
    })
    public Q create(@Valid @RequestBody R entityRequest) throws DomainException {
        return getMapper().toResponse(getService().create(getMapper().fromRequest(entityRequest)));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(description = "Atualiza um registro na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro criado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Registro não encontrado")
    })
    public Q update(@RequestBody R entityRequest, @PathVariable String id) throws DomainException {
        return getMapper().toResponse(getService().update(getMapper().fromRequest(entityRequest), id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(description = "Deleta um registro da base de dados")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        getService().delete(id);

        return ResponseEntity.noContent().build();
    }

}