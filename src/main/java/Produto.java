import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Produto {
public static void main(String[] args) {
    
}
    public String descricao;
    public double preco;
    public int estoque;
    public Scanner sc = new Scanner(System.in);

    public Produto(String descricao, double preco, int estoque, Scanner sc) {
        System.out.println("Qual é a descrição do produto?");
        this.descricao = sc.nextLine();

        System.out.println("Qual o preço deste produto?");
        this.preco = sc.nextDouble();

        System.out.println("Qual é o estoque deste produto?");
        this.estoque = sc.nextInt();

        cadastrar_produto(this.descricao, this.preco, this.estoque);
    }

    public static void cadastrar_produto(String descricao, double preco, int estoque) {

        long id_fornecedor = Consultar_ids.consultar_id_fornecedor();

        try (Connection connection = Conexao.getConnection()) {
            String sintaxe = "INSERT INTO produto (descricao,preco,estoque,id_fornecedor) VALUES (?,?,?,?)";
            try (PreparedStatement inserindo = connection.prepareStatement(sintaxe)) {
                inserindo.setString(1, descricao);
                inserindo.setDouble(2, preco);
                inserindo.setInt(3, estoque);
                inserindo.setLong(4, id_fornecedor);

                inserindo.executeUpdate();

            } catch (SQLException ex) {
                throw new RuntimeException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}