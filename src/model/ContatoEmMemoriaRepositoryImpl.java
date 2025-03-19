package model;

import java.util.List;

public class ContatoEmMemoriaRepositoryImpl implements IContatoRepository {

    private List<Contato> contatos;

    @Override
    public void salvar(Contato contato) {
        this.contatos.add(contato);
    }

    @Override
    public Contato atualizar(Contato contato) {
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
    public Contato buscarPorEmail(String email) {
        return this.contatos
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Contato> buscarTodos() {
        return this.contatos;
    }

}
