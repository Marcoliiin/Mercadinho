import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sale {

    private final long productId;
    private final long clientId;
    private final long sellerId;
    private final int totalValue;
    private final int quantitySold;
    private final Scanner sc = new Scanner(System.in);

    public Sale(long productId, long clientId, long sellerId, int totalValue, int quantitySold, Scanner sc) {
        System.out.println("Qual a quantidade vendida deste produto?");
        quantitySold = sc.nextInt();
        sc.nextLine();

        this.quantitySold = quantitySold;
        this.productId = ConsultingIds.consultingProductId();
        this.clientId = ConsultingIds.consultingClientId();
        this.sellerId = ConsultingIds.consultingSellerId();
        this.totalValue = (this.quantitySold * ConsultingCharacteristics.consultingProductPrice(this.productId));

    }

    public long createSale() {
        long saleId = 0;
        try (Connection connection = Connecting.getConnection()) {
            String sql = "INSERT INTO venda (id_cliente,id_vendedor,valor_total) VALUES (?,?,?)";
            try (PreparedStatement enteringSale = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                enteringSale.setLong(1, this.clientId);
                enteringSale.setLong(2, this.sellerId);
                enteringSale.setLong(3, this.totalValue);

                enteringSale.executeUpdate();

                ResultSet saleIdReturn = enteringSale.getGeneratedKeys();

                if (saleIdReturn.next()) {
                    saleId = saleIdReturn.getLong(1);
                }

            } catch (SQLException exception) {
                System.out.println("Erro ao cadastrar a venda: " + exception.getMessage());
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            System.err.println("Erro ao se conectar ao banco: " + exception.getMessage());
            exception.printStackTrace();
        }

        return saleId;
    }

    public void createSaleItem() {
        long saleId = createSale();

        int productPrice = (this.totalValue / this.quantitySold);

        try (Connection connection = Connecting.getConnection()) {
            String insert = "INSERT INTO venda_item (id_produto,id_venda,preco_produto,quantidade_vendida,valor_total) VALUES (?,?,?,?,?)";
            try (PreparedStatement enteringSaleItem = connection.prepareStatement((insert))) {
                enteringSaleItem.setLong(1, this.productId);
                enteringSaleItem.setLong(2, saleId);
                enteringSaleItem.setInt(3, productPrice);
                enteringSaleItem.setInt(4, this.quantitySold);
                enteringSaleItem.setInt(5, this.totalValue);

                enteringSaleItem.executeUpdate();

            } catch (SQLException exception) {
                System.err.println("Erro ao cadastrar a venda_item: " + exception.getMessage());
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            System.err.println("Erro ao se conectar ao banco: " + exception.getMessage());
            exception.printStackTrace();
        }

        reduceStock();
        sellerSale();
    }

    public void reduceStock() {
        try (Connection connection = Connecting.getConnection()) {
            String sql = "UPDATE produto SET estoque = estoque - " + this.quantitySold + " WHERE ID = " + this.productId;
            try (PreparedStatement update = connection.prepareStatement(sql)) {
                update.executeUpdate();
            } catch (SQLException exception) {
                System.err.println("Erro ao atualizar a tabela produto: " + exception.getMessage());
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            System.err.println("Erro ao se conectar ao banco: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    public void sellerSale() {
        try (Connection connection = Connecting.getConnection()) {

            String sql = "UPDATE vendedor SET num_vendas = num_vendas + 1, valor_vendas = valor_vendas + " + this.totalValue + " WHERE ID = " + this.sellerId;
            try (PreparedStatement update = connection.prepareStatement(sql)) {
                update.executeUpdate();
            } catch (SQLException exception) {
                System.err.println("Erro ao atualizar a tabela vendedor: " + exception.getMessage());
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            System.err.println("Erro ao se conectar ao banco: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
