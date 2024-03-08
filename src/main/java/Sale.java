import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Sale {

    public long productId;
    public long clienteId;
    public long sellerId;
    public int totalValue;
    public int quantitySold;
    public Scanner sc = new Scanner(System.in);

    public Sale(long productId, long clientId, long sellerId, int totalValue, int quantitySold, Scanner sc) {
        System.out.println("Qual a quantidade vendida deste produto?");
        this.quantitySold = sc.nextInt();

        this.productId = ConsultingIds.consultingProductId();
        this.clienteId = ConsultingIds.consultingClientId();
        this.sellerId = ConsultingIds.consultingSellerId();
        this.totalValue = (this.quantitySold * ConsultingCharacteristics.consultingProductPrice(this.productId));

        long saleId = 0;
        saleId = createSale(this.sellerId, this.clienteId, this.totalValue,saleId);
    }

    public static  long createSale(long sellerId, long clienteId, long totalValue, long saleId) {

        try (Connection connection = Connecting.getConnection()) {
            String sql = "INSERT INTO venda (Vid_cliente,id_vendedor,valor_total) VALUES (?,?,?)";
            try (PreparedStatement enteringSale = connection.prepareStatement(sql)) {
                enteringSale.setLong(1, clienteId);
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
}
