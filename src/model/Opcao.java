package model;

public enum Opcao {
    CRIAR(1), ATUALIZAR(2), DELETAR(3), LISTAR(4), BUSCAR(5), SAIR(6);

    private final int id;

    Opcao(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Opcao fromId(int id) {
        for (Opcao opcao : Opcao.values()) {
            if (opcao.getId() == id) {
                return opcao;
            }
        }
        throw new RuntimeException("Opção inválida: " + id);
    }
}
