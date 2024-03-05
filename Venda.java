import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Venda {
    public static void main(String[] args) {
    cadastrar_pedido();
    }
    public static int perguntar_quantidade() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual a quantidade vendido do produto?");
        int quantidade_vendida = sc.nextInt();
        sc.nextLine();

        return quantidade_vendida;
    }

    public static void cadastrar_pedido() {
        System.out.println("Chamada 1");
        int quantidade_vendida = perguntar_quantidade();
        System.out.println("Chamada 2");
        int id_produto = Consultar_ids.consultar_id_produto();
        System.out.println("Chamada 3");
        int preco_produto = consultar_preco(id_produto);
        System.out.println("Chamada 4");
        int valor_total = (quantidade_vendida * preco_produto);
        System.out.println("Chamada 5");
        int id_cliente = Consultar_ids.consultar_id_cliente();
        System.out.println("Chamada 6");
        int id_vendedor = Consultar_ids.consultar_id_vendedor();

        try (Connection connection = Conexao.getConnection()) {

            String sql = "INSERT INTO venda (id_cliente,id_vendedor,valor_total) VALUES (?,?)";
            try (PreparedStatement inserindo = connection.prepareStatement(sql)) {
                inserindo.setInt(1, id_cliente);
                inserindo.setInt(2, id_vendedor);
                inserindo.setInt(3, valor_total);

                inserindo.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static int consultar_preco(int id_produto) {
        int preco_produto = 0;

        try (Connection connection = Conexao.getConnection()) {

            String sql = "SELECT preco FROM produto where id = ?";
            try (PreparedStatement consultando_preco = connection.prepareStatement(sql)) {
                consultando_preco.setInt(1, id_produto);

                ResultSet query = consultando_preco.executeQuery();
                if (query.next()) {
                    preco_produto = query.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return preco_produto;
    }
}
