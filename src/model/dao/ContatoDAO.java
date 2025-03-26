package model.dao;

import java.sql.Connection;
import java.sql.Statement;

import model.ContatoVO;
import model.factory.ConnectionFactory;

public class ContatoDAO {

    public void salvar(ContatoVO contato) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = """
                    INSERT INTO contatos (nome, email, telefone) VALUES
                    ('%s', '%s', '%s');
                    """;

                    Statement statement = connection.createStatement();
                    statement.execute(String.format(query,
                            contato.getNome(),
                            contato.getEmail(),
                            contato.getTelefone()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
