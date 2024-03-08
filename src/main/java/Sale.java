import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sale {
    public static void main(String[] args) {

        long productId = 0;
        long clientId = 0;
        long sellerId = 0;
        int quantitySold = 0;
        int totalValue = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Antes de chamar o consrtuctor: " + quantitySold);
        Sale sale = new Sale(productId, clientId, sellerId, quantitySold, totalValue, sc);
        System.out.println("Antes de chamar o createSaleItem " + quantitySold);
        sale.createSaleItem(productId, totalValue);
    }

    public long productId;
    public long clientId;
    public long sellerId;
    public int totalValue;
    public int quantitySold;
    public Scanner sc = new Scanner(System.in);

    public Sale(long productId, long clientId, long sellerId, int totalValue, int quantitySold, Scanner sc) {
        System.out.println("Qual a quantidade vendida deste produto?");
        quantitySold = sc.nextInt();

        this.quantitySold = quantitySold;
        this.productId = ConsultingIds.consultingProductId();
        this.clientId = ConsultingIds.consultingClientId();
        this.sellerId = ConsultingIds.consultingSellerId();
        this.totalValue = (this.quantitySold * ConsultingCharacteristics.consultingProductPrice(this.productId));

    }

    public long createSale(long sellerId, long clientId, long totalValue) {
        long saleId = 0;
        try (Connection connection = Connecting.getConnection()) {
            String sql = "INSERT INTO venda (id_cliente,id_vendedor,valor_total) VALUES (?,?,?)";
            try (PreparedStatement enteringSale = connection.prepareStatement(sql)) {
                enteringSale.setLong(1, clientId);
                enteringSale.setLong(2, sellerId);
                enteringSale.setLong(3, totalValue);

                enteringSale.executeUpdate();

                ResultSet SaleIdReturn = enteringSale.getGeneratedKeys();
                if (SaleIdReturn.next()) {
                    saleId = SaleIdReturn.getLong(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return saleId;
    }

    public void createSaleItem(long productId, int totalValue) {
        long saleId = createSale(sellerId, clientId, totalValue);
        int productPrice = (totalValue / this.quantitySold);

        try (Connection connection = Connecting.getConnection()) {
            String insert = "INSERT INTO venda_item (default,id_produto,id_venda,preco_produto,quantidade,valor_total)";
            try (PreparedStatement enteringSaleItem = connection.prepareStatement((insert))) {
                enteringSaleItem.setLong(1, productId);
                enteringSaleItem.setLong(2, saleId);
                enteringSaleItem.setInt(3, productPrice);
                enteringSaleItem.setInt(4, this.quantitySold);
                enteringSaleItem.setInt(5, totalValue);

                enteringSaleItem.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
