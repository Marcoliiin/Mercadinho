import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Venda {
    public static void main(String[] args) {

        int id_produto = 0;
        int id_cliente = 0;
        int id_vendedor = 0;
        int valor_total = 0;
        int quantidade_vendida = 0;
        Scanner sc = new Scanner(System.in);

        Venda venda = new Venda(id_produto, id_cliente, id_vendedor, quantidade_vendida, valor_total, sc);
    }

    public int id_produto;
    public int id_cliente;
    public int id_vendedor;
    public int valor_total;
    public int quantidade_vendida;

    public Venda(int id_produto, int id_cliente, int id_vendedor, int valor_total, int quantidade_vendida, Scanner sc) {
        System.out.println("Qual a quantidade vendida deste produto?");
        this.quantidade_vendida = sc.nextInt();

        this.id_produto = Consultar_ids.consultar_id_produto();
        this.id_cliente = Consultar_ids.consultar_id_cliente();
        this.id_vendedor = Consultar_ids.consultar_id_vendedor();
        this.valor_total = (this.quantidade_vendida * consultar_preco(this.id_produto));

        cadastrar_pedido(this.id_vendedor, this.id_cliente, this.valor_total);
    }


    public static void cadastrar_pedido(int id_vendedor, int id_cliente, int valor_total) {
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
