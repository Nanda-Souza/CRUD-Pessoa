import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        //Variaveis de Controle
        Scanner scanner = new Scanner(System.in);
        String inputUsuario;
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        int idPessoa = 1;




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
                    System.out.println("Digite o nome:");
                    String nomePessoa = scanner.nextLine();

                    while (!Pessoa.nomeValido(nomePessoa)){
                        System.out.println("Nome inválido. Digite novamente:");
                        nomePessoa = scanner.nextLine().trim();
                    }

                    System.out.println("Digite a data de nascimento no formato aaaa-mm-dd:");
                    //String dataNascimento = LocalDate.parse(scanner.nextLine());
                    String dataNascimento = scanner.nextLine().trim();

                    while (!Pessoa.dataNascimentoValida(dataNascimento)){
                        System.out.println("Data inválida. Digite no formato aaaa-mm-dd e que não seja no futuro:");
                        dataNascimento = scanner.nextLine().trim();
                    }
                    LocalDate pessoaNascimento = LocalDate.parse(dataNascimento);

                    System.out.println("Digite o CPF:");
                    String cpfPessoa = scanner.nextLine();

                    while (!Pessoa.cpfValido(cpfPessoa) && Pessoa.cpfJaCadastrado(cpfPessoa, pessoas)){
                        System.out.println("CPF inválido, digite novamente: ");
                        cpfPessoa = scanner.nextLine();
                    }




                    break;

                case "2":
                    System.out.println("Pessoas listadas!");

                    break;

                case "3":
                    System.out.println("Cadastro de Pessoa atualizado!");

                    break;

                case "4":
                    System.out.println("Cadastro de Pessoa excluido!");

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