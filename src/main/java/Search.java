import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Search {
    public static void searchingClient() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = Connecting.getConnection();

            System.out.println("Qual é o seu nome?");
            String name = sc.nextLine();

            String sql = "SELECT * FROM clientes where UPPER(nome) = UPPER(?)";
            PreparedStatement searchingClient = connection.prepareStatement(sql);
            searchingClient.setString(1, name);
            ResultSet resultSearch = searchingClient.executeQuery();

            if (resultSearch.next()) {
                System.out.println("ID do cliente: " + resultSearch.getInt(1));
                System.out.println("Nome do cliente: " + resultSearch.getString(2));
                System.out.println("Sexo do cliente: " + resultSearch.getString(3));
                System.out.println("Endereço do cliente: " + resultSearch.getString(4));
                System.out.println("Data do cadastro: " + resultSearch.getString(5));
            } else {
                System.out.println("Não foi possível encontrar nenhum cliente com este nome.");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void searchingProduct() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = Connecting.getConnection();

            System.out.println("Qual é a descrição do seu produto?");
            String descricao = sc.nextLine();

            String sql = "SELECT * FROM produto where UPPER(descricao) = UPPER(?)";
            PreparedStatement searchingProduct = connection.prepareStatement(sql);
            searchingProduct.setString(1, descricao);
            ResultSet resultSearch = searchingProduct.executeQuery();

            if (resultSearch.next()) {
                System.out.println("ID do produto: " + resultSearch.getInt(1));
                System.out.println("Descrição do produto: " + resultSearch.getString(2));
                System.out.println("Preco do produto: " + resultSearch.getString(3));
                System.out.println("Estoque do produto: " + resultSearch.getString(4));
                System.out.println("ID do fornecedor do produto: " + resultSearch.getString(5));
                System.out.println("Data do cadastro: " + resultSearch.getString(6));
            } else {
                System.out.println("Não foi possível encontrar nenhum produto com esta descrição.");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void searchingSale() {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = Connecting.getConnection();

            System.out.println("Qual é o ID do seu pedido?");
            long saleId = sc.nextLong();
            sc.nextLine();

            String sql = "SELECT * FROM venda WHERE id = ?";
            PreparedStatement searchingSale = connection.prepareStatement(sql);
            searchingSale.setLong(1, saleId);
            ResultSet resultSearch = searchingSale.executeQuery();

            if (resultSearch.next()) {
                System.out.println("ID da venda: " + resultSearch.getLong(1));
                System.out.println("Valor total da venda: " + resultSearch.getInt(2));
                System.out.println("ID do cliente da venda: " + resultSearch.getLong(3));
                System.out.println("ID do vendedor da venda: " + resultSearch.getLong(4));
                System.out.println("Horário do cadastro da venda: " + resultSearch.getInt(5));
            } else {
                System.out.println("Não foi possível encontrar nenhuma venda com este ID.");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
