package model.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.ContatoVO;

public class ContatoEmMemoriaRepositoryImpl implements IContatoRepository {

    private List<ContatoVO> contatos = new ArrayList<>();

    @Override
    public void salvar(ContatoVO contato) {
        this.contatos.add(contato);
    }

    @Override
    public ContatoVO atualizar(ContatoVO contato) throws Exception {
        ContatoVO contatoExistente = buscarPorEmail(contato.getEmail());

        if (Objects.isNull(contatoExistente))
            throw new Exception("Contato não localizado. E-mail: " + contato.getEmail());

        contatoExistente.setNome(contato.getNome());
        contatoExistente.setEmail(contato.getEmail());
        contatoExistente.setTelefone(contato.getTelefone());

        return contatoExistente;
    }

    @Override
    public void excluir(Integer id) {
        this.contatos
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(c -> this.contatos.remove(c));
    }

    @Override
    public ContatoVO buscarPorEmail(String email) {
        return this.contatos
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ContatoVO> buscarTodos() {
        return this.contatos;
    }

}
