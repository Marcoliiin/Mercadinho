import java.util.Scanner;

public class Manipulacao {
    public static void main(String[] args) {

        long id_produto = 0;
        long id_cliente= 0;
        long id_vendedor= 0;
        long id_fornecedor= 0;
        String descricao= "";
        double preco= 0;
        int estoque= 0;
        int quantidade_vendida= 0;
        int valor_total= 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("O que você deseja fazer?\n[1]Cadastrar algo.\n[2]Consultar algo\n[3]Excluir algo\n[4]Sair");
        int escolha = sc.nextInt();
        switch (escolha) {
            case 1:
                System.out.println("O que vocẽ deseja cadastrar?\n[1]Produtos\n[2]Pedido");
                System.out.println("[3]Cliente\n[4]Fornecedor[5]Vendedor");
                escolha = sc.nextInt();
                switch (escolha) {
                    case 1:
                        Produto produto = new Produto(descricao, preco, estoque, sc);
                        break;
                    case 2:
                        Venda venda = new Venda(id_produto, id_cliente, id_vendedor, quantidade_vendida, valor_total, sc);
                        break;
                    case 3:
                        Clientes clientes = new Clientes();
                        break;
                    case 4:
                        Fornecedor Fornecedor = new Fornecedor();
                        break;
                    case 5:
                        Vendedor vendedor = new Vendedor();
                        break;
                    default:
                        System.out.println("Esta não é uma opção válida.");
                        break;
                }
            default:
                System.out.println("Esta não é uma opção válida.");
                break;
        }
    }
}