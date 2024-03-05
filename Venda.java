import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Venda {
   
   try(Scanner sc = new Scanner(System.in);
   Connection connection = Conexao.getConnection()){

    System.out.println("Qual é o ID do vendedor desta venda?");
    int resposta1 = sc.nextInt();
    sc.nextLine();

     int id_vendedor = Vinculos.consultar_id_vendedor(resposta1);
    
    System.out.println("Qual é ID do cliente desta venda?");
    int resposta2 = sc.nextInt();
    sc.nextLine();

    int id_cliente = Vinculos.consultar_id_cliente(resposta2);

  String sql = "INSERT INTO venda (id_cliente,id_vendedor) VALUES (?,?)" ;
  try(PreparedStatement inserindo = connection.PreparedStatement(sql)){
    inserindo.setInt(1,id_cliente);
    inserindo.setInt(2,id,vendedor);

    inserindo.executeUpdate();

  } catch (SQLException e){
    throw new RuntimeException();
  }catch (SQLException e) {
            e.printStackTrace();
   }
} 
}
