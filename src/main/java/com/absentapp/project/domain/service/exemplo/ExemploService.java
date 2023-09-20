package com.absentapp.project.domain.service.exemplo;

import com.absentapp.project.domain.core.exception.DomainException;
import com.absentapp.project.domain.core.service.BaseService;
import com.absentapp.project.domain.entity.Exemplo;
import com.absentapp.project.domain.repository.exemplo.ExemploRepository;
import org.springframework.stereotype.Service;

@Service
public class ExemploService extends BaseService<Exemplo> implements IExemploService {
    protected ExemploService(final ExemploRepository repository) {
        super(repository);
    }

    @Override
    public Exemplo ativar(String id, Boolean situacao) throws DomainException {
        var exemplo = this.findById(id);

        exemplo.setAtivo(situacao);

        return this.getRepository().save(exemplo);
    }

    @Override
    public ExemploRepository getRepository() {
        return (ExemploRepository) super.getRepository();
    }
}
