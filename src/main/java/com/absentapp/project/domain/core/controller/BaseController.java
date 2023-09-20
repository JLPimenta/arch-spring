package com.absentapp.project.domain.core.controller;

import com.absentapp.project.domain.core.entity.BaseEntity;
import com.absentapp.project.domain.core.exception.DomainException;
import com.absentapp.project.domain.core.mapper.IBaseMapper;
import com.absentapp.project.domain.core.service.IBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Operation(description = "Cria um novo registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar criação do novo registro.")
    })
    public Q create(@Valid @RequestBody R entityRequest) throws DomainException {
        return mapper.toResponse(service.create(mapper.fromRequest(entityRequest)));
    }

    @GetMapping
    @Operation(description = "Busca todos os registros com paginação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista com paginação retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro de passagem do parâmetro de ordenação.")
    })
    public Page<Q> findAll(@SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)}) Pageable pageable) throws DomainException {
        return service.findAll(pageable).map(mapper::toResponse);
    }

    @GetMapping("/all")
    @Operation(description = "Busca todos os registros sem paginação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista sem paginação retornada com sucesso."),
            @ApiResponse(responseCode = "500", description = "Erro de passagem do parâmetro de ordenação.")
    })
    public List<Q> findAll(@SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.ASC)
    }) Sort sort) throws DomainException {
        return mapper.toListResponse(service.findAll(sort));
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca registro específico por id.")
    public Q findById(@PathVariable String id) throws DomainException {
        return mapper.toResponse(service.findById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(description = "Atualiza um registro na base de dados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro criado com sucesso."),
            @ApiResponse(responseCode = "500", description = "Registro não encontrado")
    })
    public Q update(@RequestBody R entityRequest, @PathVariable String id) throws DomainException {
        return mapper.toResponse(service.update(mapper.fromRequest(entityRequest), id));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(description = "Deleta um registro da base de dados")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}