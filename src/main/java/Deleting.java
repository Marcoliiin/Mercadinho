import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Deleting {

 private int entityId;
 private String table;
 Scanner sc = new Scanner(System.in);

 public static void deletingEntity(){
    int entityId = 0;
    String table = "";
   System.out.println("CUIDADO!!!\n A exclusão de entidades NÃO PODE ser revertida!!!");
  System.out.println("Qual é a tabela do produto que você deseja excluir?");
  this.table = sc.nextString(); 

  System.out.println("Qual é o ID da entidade que você quer deletar?");
  this.entityId = sc.nextInt();

  try{ 
    Connection connection = Connecting.getConnecting();
    
    String sql = "DELETE * from " + this.table + " WHERE ID = ?";
    try(PreparedStatement deletingEntity = connection.preparedStatement(sql)) {
        deletingEntity.setInt(1,this.id);

 System.out.println("ATENÇÃO!!!\n Você realmente quer realizar a exclusão desta entidade?\n[1]Sim\n[2]Não");
 String choise = sc.nextLine();

 switch(choise){
    case 1: 
    deletingEntity.executeUpdate(sql);
    break;
    case 2:
        return;
 }


    }catch (SQLException exception){
    exception.printStackTrace();

  } catch (SQLException exception){
    exception.printStackTrace();
  }
 }
}
}