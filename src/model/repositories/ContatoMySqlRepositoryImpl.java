package model.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.ContatoVO;
import model.dao.ContatoDAO;

public class ContatoMySqlRepositoryImpl implements IContatoRepository {

    private final ContatoDAO dao;
    private final Logger log;

    public ContatoMySqlRepositoryImpl(ContatoDAO dao) {
        this.dao = dao;
        this.log = Logger.getLogger(ContatoMySqlRepositoryImpl.class.getName());
    }

    @Override
    public void salvar(ContatoVO contato) throws Exception {
        this.dao.salvar(contato);
    }

    @Override
    public ContatoVO atualizar(ContatoVO contato) throws Exception {
        try {
            ContatoVO resultado = this.dao.buscarPorEmail(contato.getEmail());
            if (Objects.isNull(resultado))
                throw new Exception("Contato não encontrado. E-mail: %s.".formatted(contato.getEmail()));
            this.dao.atualizar(contato);
            return this.dao.buscarPorEmail(contato.getEmail());
        } catch (Exception e) {
            log.log(Level.WARNING, e.getLocalizedMessage(), e);
            throw new Exception(e);
        }
    }

    @Override
    public void excluir(Integer id) throws SQLException {
        this.dao.excluir(id);
    }

    @Override
    public ContatoVO buscarPorEmail(String email) {
        return this.dao.buscarPorEmail(email);
    }

    @Override
    public List<ContatoVO> buscarTodos() {
        return dao.buscarTodos();
    }

}
