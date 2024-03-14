import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Product {

    private final String description;
    private final double price;
    private final int stock;
    private final Scanner sc = new Scanner(System.in);

    public Product(String descricao, double preco, int estoque, Scanner sc) {
        System.out.println("Qual é a descrição do produto?");
        this.description = sc.nextLine();

        System.out.println("Qual o preço deste produto?");
        this.price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Qual é o estoque deste produto?");
        this.stock = sc.nextInt();
        sc.nextLine();

        createProduct(this.description, this.price, this.stock);
    }

    public static void createProduct(String description, double price, int stock) {

        long supplierId = ConsultingIds.consultingSupplierId();

        try (Connection connection = Connecting.getConnection()) {
            String sintaxe = "INSERT INTO produto (descricao,preco,estoque,id_fornecedor) VALUES (?,?,?,?)";
            try (PreparedStatement enteringProduct = connection.prepareStatement(sintaxe)) {
                enteringProduct.setString(1, description);
                enteringProduct.setDouble(2, price);
                enteringProduct.setInt(3, stock);
                enteringProduct.setLong(4, supplierId);

                enteringProduct.executeUpdate();

            } catch (SQLException exception) {
                System.err.println("Erro ao criar o produto; " + exception.getMessage());
                exception.printStackTrace();
            }

        } catch (SQLException exception) {
            System.err.println("Erro ao se conectar ao banco: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
