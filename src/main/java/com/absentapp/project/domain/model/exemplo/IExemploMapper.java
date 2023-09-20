package com.absentapp.project.domain.model.exemplo;

import com.absentapp.project.domain.core.mapper.IBaseMapper;
import com.absentapp.project.domain.entity.Exemplo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IExemploMapper extends IBaseMapper<Exemplo, ExemploRequest, ExemploResponse> {
}