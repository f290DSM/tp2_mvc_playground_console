package model;

import java.util.List;
public interface IContatoRepository {
    void salvar(Contato contato);
    Contato atualizar(Contato contato);
    void excluir(Integer id);
    Contato buscarPorEmail(String email);
    List<Contato> buscarTodos();
}
