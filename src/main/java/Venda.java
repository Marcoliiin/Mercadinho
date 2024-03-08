import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Venda {
    
        public long id_produto;
        public long id_cliente;
        public long id_vendedor;
        public int valor_total;
        public int quantidade_vendida;
        public Scanner sc = new Scanner(System.in);

    public Venda(long id_produto, long id_cliente, long id_vendedor, int valor_total, int quantidade_vendida, Scanner sc) {
        System.out.println("Qual a quantidade vendida deste produto?");
        this.quantidade_vendida = sc.nextInt();

        this.id_produto = Consultar_ids.consultar_id_produto();
        this.id_cliente = Consultar_ids.consultar_id_cliente();
        this.id_vendedor = Consultar_ids.consultar_id_vendedor();
        this.valor_total = (this.quantidade_vendida * Consultar_caracteristicas.consultar_preco_produto(this.id_produto));

        long id_venda = 0;
        id_venda = cadastrar_pedido(this.id_vendedor, this.id_cliente, this.valor_total,id_venda);
    }

    public static  long cadastrar_pedido(long id_vendedor, long id_cliente, long valor_total, long id_venda) {

        try (Connection connection = Conexao.getConnection()) {
            String sql = "INSERT INTO venda (Vid_cliente,id_vendedor,valor_total) VALUES (?,?,?)";
            try (PreparedStatement inserindo = connection.prepareStatement(sql)) {
                inserindo.setLong(1, id_cliente);
                inserindo.setLong(2, id_vendedor);
                inserindo.setLong(3, valor_total);

                inserindo.executeUpdate();


                ResultSet retorno_id_venda = inserindo.getGeneratedKeys();
                if (retorno_id_venda.next()) {
                    id_venda = retorno_id_venda.getLong(1);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
        return id_venda; 
    } 
}
