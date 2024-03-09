import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {

        long productId = 0;
        long clientId = 0;
        long sellerId = 0;
        long supplierId = 0;
        String description = "";
        double price = 0;
        int stock = 0;
        int quantitySold = 0;
        int totalValue = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("O que você deseja fazer?\n[1]Cadastrar algo.\n[2]Consultar algo.\n[3]Excluir algo.\n[4]Sair.");
        int choise = sc.nextInt();
        sc.nextLine();

        switch (choise) {
            case 1:
                System.out.println("O que vocẽ deseja cadastrar?\n[1]Produto.\n[2]Pedido.");
                System.out.println("[3]Cliente.\n[4]Fornecedor.\n[5]Vendedor.");
                choise = sc.nextInt();
                sc.nextLine();

                switch (choise) {
                    case 1:
                        Product product = new Product(description, price, stock, sc);
                        break;
                    case 2:
                        Sale sale = new Sale(productId, clientId, sellerId, quantitySold, totalValue, sc);
                        sale.createSaleItem();
                        break;
                    case 3:
                        Clients clients = new Clients();
                        break;
                    case 4:
                        Supplier supplier = new Supplier();
                        break;
                    case 5:
                        Seller seller = new Seller();
                        break;
                    default:
                        System.out.println("Esta não é uma opção válida.");
                        break;
                }
            case 2:
                System.out.println("O que você deseja consultar?\n[1]Produto.\n[2]Pedido.");
                System.out.println("[3]Cliente.\n[4]Fornecedor.\n[5]Vendedor.");
                choise = sc.nextInt();
                sc.nextLine();

                switch (choise) {
                    case 1:
                        Search.searchingProduct();
                        break;
                    case 2:
                        break;
                    case 3:
                        Search.searchingClient();
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Esta não é uma opção válida.");
                        break;
                }
            case 4:
                return;
            default:
                System.out.println("Esta não é uma opção válida.");
                break;
        }
    }
}
