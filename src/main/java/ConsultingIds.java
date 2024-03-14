import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class ConsultingIds {
    public static long consultingSupplierId() {
        long supplierReturn = 0;
        Scanner sc = new Scanner(System.in);

        try (Connection connection = Connecting.getConnection()) {

            System.out.println("Qual é o ID do fornecedor deste produto?");
            long supplierId = sc.nextLong();

            String sql = "SELECT id FROM fornecedor WHERE id = ?";
            try (PreparedStatement consultingId = connection.prepareStatement(sql)) {
                consultingId.setLong(1, supplierId);

                try (ResultSet query = consultingId.executeQuery()) {
                    if (query.next()) {
                        supplierReturn = query.getLong(1);
                    }
                } catch (SQLException exception) {
                    exception.getMessage();
                    exception.printStackTrace();
                }
            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }

        return supplierReturn;
    }

    public static long consultingClientId() {
        long clientReturn = 0;
        Scanner sc = new Scanner(System.in);
        try (Connection connection = Connecting.getConnection()) {

            System.out.println("Qual é o ID do cliente da venda?");
            long clientId = sc.nextLong();

            String sql = "SELECT id FROM clientes WHERE id = ?";
            try (PreparedStatement consultingId = connection.prepareStatement(sql)) {
                consultingId.setLong(1, clientId);

                try (ResultSet query = consultingId.executeQuery()) {
                    if (query.next()) {
                        clientReturn = query.getLong(1);
                    }

                } catch (SQLException exception) {
                    exception.getMessage();
                    exception.printStackTrace();
                }
            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }
        return clientReturn;
    }

    public static long consultingSellerId() {
        long sellerReturn = 0;
        Scanner sc = new Scanner(System.in);

        try (Connection connection = Connecting.getConnection()) {

            System.out.println("Qual é o ID do vendedor da venda?");
            long sellerId = sc.nextLong();

            String sql = "SELECT id FROM vendedor WHERE id = ?";
            try (PreparedStatement consultingId = connection.prepareStatement(sql)) {
                consultingId.setLong(1, sellerId);

                try (ResultSet query = consultingId.executeQuery()) {
                    if (query.next()) {
                        sellerReturn = query.getLong(1);
                    }
                } catch (SQLException exception) {
                    exception.getMessage();
                    exception.printStackTrace();
                }
            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }
        return sellerReturn;
    }

    public static long consultingProductId() {
        long productReturn = 0;
        Scanner sc = new Scanner(System.in);

        try (Connection connection = Connecting.getConnection()) {

            System.out.println("Qual o ID do item que foi vendido?");
            long itenId = sc.nextLong();

            String sql = "SELECT id from produto where id = ?";
            try (PreparedStatement consultingId = connection.prepareStatement(sql)) {
                consultingId.setLong(1, itenId);

                try (ResultSet query = consultingId.executeQuery()) {
                    if (query.next()) {
                        productReturn = query.getLong(1);
                    }
                } catch (SQLException exception) {
                    exception.getMessage();
                    exception.printStackTrace();
                }
            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }
        return productReturn;
    }

    public static long consultingSaleId() {
        long saleReturn = 0;
        Scanner sc = new Scanner(System.in);

        try (Connection connection = Connecting.getConnection()) {

            System.out.println("Qual o ID da venda?");
            long saleId = sc.nextLong();

            String sql = "SELECT ID from venda where ID = ?";
            try (PreparedStatement consultingId = connection.prepareStatement(sql)) {
                consultingId.setLong(1, saleId);

                try (ResultSet query = consultingId.executeQuery()) {
                    if (query.next()) {
                        saleReturn = query.getLong(1);
                    }
                } catch (SQLException exception) {
                    exception.getMessage();
                    exception.printStackTrace();
                }
            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }
        return saleReturn;
    }
}
