import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deleting {

    private int entityId;
    private String table;
    Scanner sc = new Scanner(System.in);

    public Deleting() {

        System.out.println("CUIDADO!!!\nA exclusão de entidades NÃO PODE ser revertida!!!");
        System.out.println("Qual é a tabela do produto que você deseja excluir?");
        this.table = sc.nextLine();

        switch (this.table) {
            case "venda":
            case "venda_item":
            case "caixa":
                System.out.println("Não é possível realizar exclusões nesta tabela!!!");
                break;

            case "vendedor":
            case "clientes":
            case "produto":
            case "fornecedor":
                System.out.println("Qual é o ID desta entidade que você quer deletar?");
                this.entityId = sc.nextInt();

                deletingEntity();
                break;

            default:
                System.out.println("Está tabela não existe!!!");
                break;
        }
    }

    public void deletingEntity() {
        try {
            Connection connection = Connecting.getConnection();

            String sql = "DELETE from " + this.table + " WHERE ID = ?";
            try (PreparedStatement deletingEntity = connection.prepareStatement(sql)) {
                deletingEntity.setInt(1, this.entityId);

                System.out.println("ATENÇÃO!!!\nVocê realmente quer realizar a exclusão desta entidade?\n[1]Sim\n[2]Não");
                int choise = sc.nextInt();

                switch (choise) {
                    case 1:
                        deletingEntity.executeUpdate();
                        break;
                    case 2:
                        return;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
        }
    }
}
