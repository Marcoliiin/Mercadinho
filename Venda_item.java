import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Venda_item {
    public static void main(String[] args) {
    }
    
    try (Scanner sc = new Scanner(System.in);
    Connection connection = Conexao.getConnection()){

        System.out.println("Qual o ID do produto desta venda?");
        int id_produto = sc.nextInt();

        String sql = "SELECT ID from produto where id = ?";
        try(prepareStatement consultando_id = connection.PreparedStatement(sql)){
            inserindo.setInt(1,id_produto);

            try (ResultSet query = consultando_id.executeQuery()) {
                    if (query.next()) {
                        retorno_consulta_produto = query.getInt(1);
                    }
                }
        }
    }
}
