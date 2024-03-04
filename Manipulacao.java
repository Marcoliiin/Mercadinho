import java.util.Scanner;

public class Manipulacao {
    public static void main(String[] args) {
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
                        Produto.cadastrar_produto();
                        break;
                    case 2:
                        break;
                    case 3:
                        Clientes.criar_cliente();
                        break;
                    case 4:
                        Fornecedor.criar_fornecedor();
                        break;
                    case 5:
                        Vendedor.criar_vendedor();
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
