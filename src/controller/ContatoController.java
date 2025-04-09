package controller;

import model.ContatoDTO;
import model.services.ContatoService;

import java.util.List;

public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    public void salvar(ContatoDTO dto) {
        service.salvar(dto);
    }
    public ContatoDTO buscarContatoPorEmail(String email) {
        return service.buscarPorEmail(email);
    }

    public List<ContatoDTO> listarContatos() {
        return service.buscarTodos();
    }

    //TODO: Incluir o delete

    //TODO: Incluir o atualizar
}
