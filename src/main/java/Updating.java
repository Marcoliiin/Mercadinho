import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSetMetaData;

public class Updating {

    private String tableName;
    private String columnName;
    private String entityValue;
    private int entityId;
    Scanner sc = new Scanner(System.in);

    public Updating() {
        System.out.println("Qual tabela você quer atualizar?");
        this.tableName = sc.nextLine();

        System.out.println("Qual informação você quer atualizar?");
        this.columnName = sc.nextLine();

        System.out.println("Qual o ID da entidade que você quer atualizar?");
        this.entityId = sc.nextInt();
        sc.nextLine();

        System.out.println("Escreva o que você quer inserir:");
        this.entityValue = sc.nextLine();

        gettingColumnType();

        //esse método foi criado para definir se a coluna é uma string(12) ou um integer(4)
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
                    System.out.println("Tipo da coluna: " + columnType);
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.getMessage();
        }
        return columnType;
    }

    public void updatingEntity() {

        try {
            Connection connection = Connecting.getConnection();

            String sql = "UPDATE " + columnName + " FROM " + tableName + " WHERE ID = " + this.entityId;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
