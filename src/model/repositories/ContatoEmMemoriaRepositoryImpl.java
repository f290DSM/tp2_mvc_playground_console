package model.repositories;

import java.util.List;

import model.ContatoVO;

public class ContatoEmMemoriaRepositoryImpl implements IContatoRepository {

    private List<ContatoVO> contatos;

    @Override
    public void salvar(ContatoVO contato) {
        this.contatos.add(contato);
    }

    @Override
    public ContatoVO atualizar(ContatoVO contato) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
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
