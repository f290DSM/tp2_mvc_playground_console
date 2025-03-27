package model.dao;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.ContatoVO;

public class ContatoDAO {

    private final Connection connection;
    private final Logger log;

    public ContatoDAO(Connection connection) {
        this.connection = connection;
        this.log = Logger.getLogger(ContatoDAO.class.getName());
    }

    public void salvar(ContatoVO contato) {
        try {
            String query = """
                    INSERT INTO contatos (nome, email, telefone) VALUES
                    ('%s', '%s', '%s');
                    """;

            String sql = query.formatted(
                    contato.getNome(),
                    contato.getEmail(),
                    contato.getTelefone());

            Statement statement = connection.createStatement();
            statement.execute(sql);

            log.info("Contato criado com sucesso");

            statement.close();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Falha ao salvar novo contato.", e);
            throw new RuntimeException(e);
        }
    }

    ContatoVO atualizar(ContatoVO contato) {
        return null;

    }

    void excluir(Integer id) {

    }

    public ContatoVO buscarPorEmail(String email) {
        String sql = "SELECT id, nome, email, telefone FROM contatos WHERE email = " + email;
        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(sql)) {
            if (rst.next()) {
                ContatoVO contatoVO = new ContatoVO(
                        rst.getInt("id"),
                        rst.getString("nome"),
                        rst.getString("email"),
                        rst.getString("telefone"));
                log.info("Consulta realizada com sucesso.");
                return contatoVO;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Falha ao consultar contatos", e);
        }
        return null;
    }

    public List<ContatoVO> buscarTodos() throws SQLException {
        List<ContatoVO> contatos = new ArrayList<>();
        String sql = "SELECT id, nome, email, telefone FROM contatos";

        try (Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery(sql);) {

            while (rst.next()) {
                ContatoVO contatoVO = new ContatoVO(
                        rst.getInt("id"),
                        rst.getString("nome"),
                        rst.getString("email"),
                        rst.getString("telefone"));
                contatos.add(contatoVO);
            }
            log.info("Consulta realizada com sucesso.");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Falha ao consultar contatos", e);
        }
        return contatos;
    }

}
