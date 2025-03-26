package model.repositories;

import java.util.List;

import model.ContatoVO;
public interface IContatoRepository {
    void salvar(ContatoVO contato);
    ContatoVO atualizar(ContatoVO contato);
    void excluir(Integer id);
    ContatoVO buscarPorEmail(String email);
    List<ContatoVO> buscarTodos();
}
