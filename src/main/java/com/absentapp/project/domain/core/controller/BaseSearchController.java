package com.absentapp.project.domain.core.controller;

import com.absentapp.project.api.config.exception.ResponseError;
import com.absentapp.project.domain.core.entity.BaseEntity;
import com.absentapp.project.domain.core.exception.DomainException;
import com.absentapp.project.domain.core.mapper.IBaseMapper;
import com.absentapp.project.domain.core.service.IBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * T ⇾ Entity
 * R ⇾ Request model
 * Q ⇾ Response model
 */
@Getter
public abstract class BaseSearchController<T extends BaseEntity, R, Q> {

    private final IBaseService<T> service;
    private final IBaseMapper<T, R, Q> mapper;

    protected BaseSearchController(final IBaseService<T> service, final IBaseMapper<T, R, Q> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(description = "Busca todos os registros com paginação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista com paginação retornada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de passagem do parâmetro de ordenação.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))
            })
    })
    public Page<Q> findAll(@SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)}) Pageable pageable) throws DomainException {
        return getService().findAll(pageable).map(getMapper()::toResponse);
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca registro específico por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso."),
            @ApiResponse(responseCode = "409", description = "Item não encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class))
            })
    })
    public Q findById(@PathVariable String id) {
        return getMapper().toResponse(getService().findById(id));
    }
}
