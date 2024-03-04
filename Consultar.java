package jdbc.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class Pesquisar {
    public static void pesquisar_clientes() {
        try (Scanner sc = new Scanner(System.in)) {
            Connection connection = Conexao.getConnection();

            System.out.println("Qual é o seu nome?");
            String nome = sc.nextLine();

            String sql = "SELECT * FROM clientes where nome = ?";
            PreparedStatement sintaxe = connection.prepareStatement(sql);
            sintaxe.setString(1, nome);
            ResultSet resultado = sintaxe.executeQuery();

            if (resultado.next()) {
                System.out.println("ID do cliente: " + resultado.getInt(1));
                System.out.println("Nome do cliente: " + resultado.getString(2));
                System.out.println("Sexo do cliente: " + resultado.getString(3));
                System.out.println("Endereço do cliente: " + resultado.getString(4));
                System.out.println("Data do cadastro: " + resultado.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
