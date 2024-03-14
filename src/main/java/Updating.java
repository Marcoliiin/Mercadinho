import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSetMetaData;

public class Updating {

    private final String tableName;
    private final String columnName;
    private final String entityValue;
    private int entityId;
    Scanner sc = new Scanner(System.in);

    public Updating() {

        int columnType = 0;

        System.out.println("Qual tabela você quer atualizar?");
        this.tableName = sc.nextLine();

        System.out.println("Qual coluna você quer atualizar?");
        this.columnName = sc.nextLine();

        System.out.println("Qual o ID da entidade que você quer atualizar?");
        this.entityId = sc.nextInt();
        sc.nextLine();

        System.out.println("Escreva o que você quer inserir:");
        this.entityValue = sc.nextLine();

        updatingEntity(columnType);
    }

    public int gettingColumnType() {
        int columnType = 0;

        try {
            Connection connection = Connecting.getConnection();

            String sql = "SELECT " + this.columnName + " FROM " + tableName + " limit 1";
            try {
                PreparedStatement select = connection.prepareStatement(sql);
                ResultSet query = select.executeQuery();

                if (query.next()) {
                    ResultSetMetaData metadata = select.getMetaData();
                    columnType = metadata.getColumnType(1);
                }

            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }
        return columnType;
    }

    public void updatingEntity(int columnType) {
        String entityValue = this.entityValue;
        columnType = gettingColumnType();

        try {
            Connection connection = Connecting.getConnection();

            String sql = "UPDATE " + this.tableName + " SET " + this.columnName + " = '" + entityValue + "' WHERE ID = " + this.entityId;
            try (PreparedStatement updatingEntity = connection.prepareStatement(sql)) {
                if (columnType == 12) {
                    updatingEntity.executeUpdate();
                } else {

                    int intEntityValue = Integer.parseInt(entityValue);
                    updatingEntity.executeUpdate();
                }
            } catch (SQLException exception) {
                exception.getMessage();
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
            exception.printStackTrace();
        }
    }
}
