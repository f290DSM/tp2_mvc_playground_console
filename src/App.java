import java.sql.Connection;
import java.sql.ResultSet;

import model.ContatoVO;
import model.dao.ContatoDAO;
import model.factory.ConnectionFactory;

public class App {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionFactory.getConnection();
        ContatoDAO dao = new ContatoDAO(connection);

        ContatoVO c1 = new ContatoVO(null,
                "Alfredo Silva",
                "a.silva@gmail.com",
                "(19)98888-7777");

        dao.salvar(c1);

        // ResultSet rst = connection
        // .createStatement()
        // .executeQuery("select version() as versao;");

        // if (rst.next()) {
        // System.out.println(rst.getString("versao"));
        // }

        // rst.close();
    }
}
