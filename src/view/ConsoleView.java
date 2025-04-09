package view;

import java.sql.Connection;
import java.sql.SQLException;

import controller.ContatoController;
import model.ContatoDTO;
import model.dao.ContatoDAO;
import model.factory.ConnectionFactory;
import model.repositories.ContatoEmMemoriaRepositoryImpl;
import model.repositories.ContatoMySqlRepositoryImpl;
import model.repositories.IContatoRepository;
import model.services.ContatoService;

public class ConsoleView {

    private ContatoController controller;

    public ConsoleView(String dbParam) {
        try {
            IContatoRepository repository = createRepository(dbParam);
            var service = new ContatoService(repository);
            controller = new ContatoController(service);
        } catch (Exception e) {
            System.out.println("Falha ao inciar aplicação");
            System.exit(0);
        }
    }

    private void salvar() {
        System.out.println("Digite o nome do contato");
        var scanner = new java.util.Scanner(System.in);
        String nome = scanner.nextLine();

        System.out.println("Digite o email do contato");
        String email = scanner.nextLine();

        System.out.println("Digite o telefone do contato");
        String telefone = scanner.nextLine();

        var dto = new ContatoDTO(null, nome, email, telefone);
        controller.salvar(dto);
        System.out.println("Contato salvo com sucesso!\n");
        scanner.close();
    }

    private void listarContatos() {
        controller.listarContatos().forEach(System.out::println);
    }

    private void buscarContatoPorEmail() {
        System.out.println("Digite o email do contato");
        var scanner = new java.util.Scanner(System.in);
        String email = scanner.nextLine();
        scanner.close();
        ContatoDTO contato = controller.buscarContatoPorEmail(email);
        if (contato == null) {
            System.out.println("Contato não encontrado");
            return;
        }
        System.out.println(contato);
    }

    public void iniciar() {
        System.out.println("Bem-vindo ao sistema de cadastro de contatos\n");
        var scanner = new java.util.Scanner(System.in);
        int opcao = -1;

        while (true) {
            exibrMenu();            
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    salvar();
                    break;
                case 2:
                    listarContatos();
                    break;
                case 3:
                    buscarContatoPorEmail();
                    break;
                case 4:
                    // TODO: Incluir atualização
                    break;
                case 5:
                    // TODO: Incuir exlusão
                    break;
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

    }

    private IContatoRepository createRepository(String param) throws SQLException {
        if (param == "-mysql") {
            Connection connection = ConnectionFactory.getConnection();
            ContatoDAO dao = new ContatoDAO(connection);
            return new ContatoMySqlRepositoryImpl(dao);
        } else {
            return new ContatoEmMemoriaRepositoryImpl();
        }
    }

    private void exibrMenu() {
        System.out.println("\nDigite 1 para cadastrar um novo contato");
        System.out.println("Digite 2 para listar todos os contatos");
        System.out.println("Digite 3 para buscar um contato por email");
        System.out.println("Digite 4 para atualizar um contato");
        System.out.println("Digite 5 para excluir um contato");
        System.out.println("Digite 0 para sair\n");
    }
}
