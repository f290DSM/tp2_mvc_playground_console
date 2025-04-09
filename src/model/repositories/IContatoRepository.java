package model.repositories;

import java.util.List;

import model.ContatoVO;
public interface IContatoRepository {
    void salvar(ContatoVO contato) throws Exception;
    ContatoVO atualizar(ContatoVO contato) throws Exception;
    void excluir(Integer id) throws Exception;
    ContatoVO buscarPorEmail(String email) throws Exception;
    List<ContatoVO> buscarTodos();
}
