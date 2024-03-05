import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Consultar_ids {
    public static int consultar_id_fornecedor() {
        int retorno_fornecedor = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é o ID do fornecedor deste produto?");
            int id_fornecedor = sc.nextInt();

            String sql = "SELECT id FROM fornecedor WHERE id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setInt(1, id_fornecedor);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_fornecedor = query.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retorno_fornecedor;
    }

    public static int consultar_id_cliente() {
        int retorno_cliente = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é o ID do cliente da venda?");
            String id_cliente = sc.nextLine();

            String sql = "SELECT id FROM clientes WHERE id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setString(1, id_cliente);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_cliente = query.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno_cliente;
    }

    public static int consultar_id_vendedor() {
        int retorno_vendedor = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é o ID do vendedor da venda?");
            String id_vendedor = sc.nextLine();

            String sql = "SELECT id FROM vendedor WHERE id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setString(1, id_vendedor);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_vendedor = query.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno_vendedor;
    }

    public static int consultar_id_produto() {
        int retorno_produto = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual o ID do item que foi vendido?");
            int id_item = sc.nextInt();

            String sql = "SELECT ID from produto where id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setInt(1, id_item);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_produto = query.getInt(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return retorno_produto;
    }

    public static int consultar_id_venda() {
        int retorno_venda = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual o ID da venda?");
            int id_venda = sc.nextInt();

            String sql = "SELECT ID from venda where ID = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setInt(1, id_venda);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_venda = query.getInt(1);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return retorno_venda;
    }
}
