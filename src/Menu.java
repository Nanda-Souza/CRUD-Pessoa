import controller.Controller;
import repository.Repository;
import service.Service;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputUsuario;
        Repository repository = new Repository();
        Service service = new Service(repository);
        Controller controller = new Controller(service);



        do{

            System.out.println("\n==== CRUD Menu ====");
            System.out.println("1 - Cadastrar Pessoa");
            System.out.println("2 - Listar Pessoas");
            System.out.println("3 - Atualizar cadastro de pessoa e/ou endereço");
            System.out.println("4 - Excluir cadastro de pessoa");
            System.out.println("5 - Mostrar a idade de uma pessoa");
            System.out.println("0 - Sair");

            inputUsuario = scanner.nextLine().trim();

            switch (inputUsuario){

                case "1":
                    controller.cadastrarPessoa();

                    break;

                case "2":
                    controller.listarPessoa();
                    break;

                case "3":
                    controller.atualizarPessoa();
                    break;

                case "4":
                    controller.excluirPessoa();
                    break;

                case "5":
                    System.out.println("Selecione a pessoa para mostrar a idade!");

                    break;

                case "0":
                    System.out.println("Fechando o programa!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (!inputUsuario.equals("0"));

    }
}