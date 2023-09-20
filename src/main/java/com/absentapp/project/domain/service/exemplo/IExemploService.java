package com.absentapp.project.domain.service.exemplo;

import com.absentapp.project.domain.core.exception.DomainException;
import com.absentapp.project.domain.core.service.IBaseService;
import com.absentapp.project.domain.entity.Exemplo;

public interface IExemploService extends IBaseService<Exemplo> {
    Exemplo ativar(String id, Boolean situacao) throws DomainException;
}
