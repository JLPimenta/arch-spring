package com.absentapp.project.api.controller.exemplo;

import com.absentapp.project.domain.core.controller.BaseController;
import com.absentapp.project.domain.entity.Exemplo;
import com.absentapp.project.domain.model.exemplo.ExemploRequest;
import com.absentapp.project.domain.model.exemplo.ExemploResponse;
import com.absentapp.project.domain.model.exemplo.IExemploMapper;
import com.absentapp.project.domain.service.exemplo.IExemploService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exemplo", produces = {"application/json"})
@Tag(name = "Exemplo", description = "Controlador de Exemplo.")
public class ExemploController extends BaseController<Exemplo, ExemploRequest, ExemploResponse> {
    protected ExemploController(final IExemploMapper mapper, final IExemploService service) {
        super(mapper, service);
    }
}
