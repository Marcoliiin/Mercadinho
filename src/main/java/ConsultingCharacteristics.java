import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultingCharacteristics {
    public static int consultingProductPrice(long product_id) {
        int productPrice = 0;

        try (Connection connection = Connecting.getConnection()) {
            String sql = "SELECT preco FROM produto where id = ?";
            try (PreparedStatement consultingPrice = connection.prepareStatement(sql)) {
                consultingPrice.setLong(1, product_id);

                ResultSet query = consultingPrice.executeQuery();
                if (query.next()) {
                    productPrice = query.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productPrice;
    }
}
