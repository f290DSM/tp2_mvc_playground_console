package model.services;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import model.ContatoDTO;
import model.ContatoVO;
import model.repositories.IContatoRepository;

public class ContatoService {

    private final IContatoRepository repository;
    private final Logger log;

    public ContatoService(IContatoRepository repository) {
        this.repository = repository;
        this.log = Logger.getLogger(ContatoService.class.getName());
    }

    public void salvar(ContatoDTO dto) {
        try {
            // TODO: Validar o dto
            validar(dto);
            // TODO: Converter dto para VO
            var contato = new ContatoVO(null,
                    dto.getNome(),
                    dto.getEmail(),
                    dto.getTelefone());

            // TODO: Salvar o contato através do repository
            repository.salvar(contato);
            log.info("Contato salvo com sucesso.");
        } catch (Exception e) {
            System.out.println("Falha ao salvar contato.");
        }

    }

    public List<ContatoDTO> buscarTodos() {
        return repository.buscarTodos()
                .stream()
                .map(contato -> new ContatoDTO(
                        contato.getId(),
                        contato.getNome(),
                        contato.getEmail(),
                        contato.getTelefone()))
                .toList();
    }

    public ContatoDTO buscarPorEmail(String email) {
        try {
            if (Objects.isNull(email)) {
                throw new IllegalArgumentException("Email não pode ser nulo");
            }
            var contato = repository.buscarPorEmail(email);
            
            if (Objects.isNull(contato)) {
                throw new IllegalArgumentException("Contato não encontrado");
            }

            return new ContatoDTO(
                    contato.getId(),
                    contato.getNome(),
                    contato.getEmail(),
                    contato.getTelefone());
        } catch (Exception e) {
            System.out.println("Falha ao buscar contato.");
            return null;
        }
       
    }

    private void validar(ContatoDTO dto) {
        if (Objects.isNull(dto)) {
            throw new IllegalArgumentException("Contato não pode ser nulo");
        }

        if (Objects.isNull(dto.getNome()) || dto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }

        if (Objects.isNull(dto.getEmail()) || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo");
        }

        if (Objects.isNull(dto.getTelefone()) || dto.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo");
        }
    }

    //TODO: incluir o detete

    //TODO: Incluir o atualizar
}
