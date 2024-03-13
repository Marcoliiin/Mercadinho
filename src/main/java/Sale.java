import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sale {

    public long productId;
    public long clientId;
    public long sellerId;
    public int totalValue;
    public int quantitySold;
    public Scanner sc = new Scanner(System.in);

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
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            throw new RuntimeException();
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

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void reduceStock() {

    }

    public void sellerSale() {

    }
    
}
