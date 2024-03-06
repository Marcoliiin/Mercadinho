import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Consultar_ids {
    public static long consultar_id_fornecedor() {
        long retorno_fornecedor = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é o ID do fornecedor deste produto?");
            long id_fornecedor = sc.nextLong();

            String sql = "SELECT id FROM fornecedor WHERE id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setLong(1, id_fornecedor);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_fornecedor = query.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retorno_fornecedor;
    }

    public static long consultar_id_cliente() {
        long retorno_cliente = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é o ID do cliente da venda?");
            long id_cliente = 1;/*sc.nextInt();*/

            String sql = "SELECT id FROM clientes WHERE id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setLong(1, id_cliente);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_cliente = query.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno_cliente;
    }

    public static long consultar_id_vendedor() {
        long retorno_vendedor = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual é o ID do vendedor da venda?");
            long id_vendedor = sc.nextLong();

            String sql = "SELECT id FROM vendedor WHERE id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setLong(1, id_vendedor);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_vendedor = query.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno_vendedor;
    }

    public static long consultar_id_produto() {
        long retorno_produto = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual o ID do item que foi vendido?");
            long id_item = sc.nextLong();

            String sql = "SELECT ID from produto where id = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setLong(1, id_item);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_produto = query.getLong(1);
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

    public static long consultar_id_venda() {
        long retorno_venda = 0;

        try (Scanner sc = new Scanner(System.in);
             Connection connection = Conexao.getConnection()) {

            System.out.println("Qual o ID da venda?");
            long id_venda = sc.nextLong();

            String sql = "SELECT ID from venda where ID = ?";
            try (PreparedStatement consultando_id = connection.prepareStatement(sql)) {
                consultando_id.setLong(1, id_venda);

                try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_venda = query.getLong(1);
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
