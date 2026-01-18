import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static boolean inputValido(String input) {
        return input != null &&
                input.matches("^[12]$");
    }

    public static boolean numeroValido(String numero) {
        return numero != null &&
                numero.matches("\\d+");
    }


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
                    String nomePessoa = scanner.nextLine().trim();

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
                    LocalDate nascimentoPessoa = LocalDate.parse(dataNascimento);

                    System.out.println("Digite o CPF, apenas numeros:");
                    String cpfPessoa = scanner.nextLine().trim();

                    while (!Pessoa.cpfValido(cpfPessoa) || Pessoa.cpfJaCadastrado(cpfPessoa, pessoas)){
                        System.out.println("CPF inválido, digite novamente: ");
                        cpfPessoa = scanner.nextLine().trim();
                    }

                    boolean cadastrarOutroEndereco = false;
                    ArrayList<Endereco> enderecosPessoa = new ArrayList<>();
                    int idEndereco= 1;

                    do {
                        System.out.println("Digite a Rua:");
                        String rua = scanner.nextLine().trim();

                        while (!Endereco.enderecoValido(rua)){
                            System.out.println("Nome de Rua inválido, digite novamente:");
                            rua = scanner.nextLine().trim();
                        }

                        System.out.println("Digite o Numero da Rua:");
                        String inputNumero = scanner.nextLine().trim();

                        while (!numeroValido(inputNumero)){
                            System.out.println("Número inválido, digite novamente:");
                            inputNumero = scanner.nextLine().trim();
                        }

                        Integer numero = Integer.parseInt(inputNumero);

                        System.out.println("Digite o Bairro:");
                        String bairro = scanner.nextLine();

                        while (!Endereco.enderecoValido(bairro)){
                            System.out.println("Bairro inválido, digite novamente:");
                            bairro = scanner.nextLine().trim();
                        }

                        System.out.println("Digite a Cidade:");
                        String cidade = scanner.nextLine();

                        while (!Endereco.enderecoValido(cidade)){
                            System.out.println("Cidade inválida, digite novamente:");
                            cidade = scanner.nextLine().trim();
                        }

                        System.out.println("Digite o Estado:");
                        String estado = scanner.nextLine();

                        while (!Endereco.enderecoValido(estado)){
                            System.out.println("Estado inválido, digite novamente:");
                            estado = scanner.nextLine().trim();
                        }

                        System.out.println("Digite o CEP, apenas numeros:");
                        String cep = scanner.nextLine();

                        while (!Endereco.cepValido(cep)){
                            System.out.println("CEP inválido, digite novamente:");
                            cep = scanner.nextLine().trim();
                        }

                        Endereco endereco = new Endereco(idEndereco++, rua, numero, bairro, cidade, estado, cep);
                        enderecosPessoa.add(endereco);

                        System.out.println("\nDeseja adicionar mais um endereço? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            cadastrarOutroEndereco = true;
                        } else {
                            cadastrarOutroEndereco = false;
                        }

                    } while (cadastrarOutroEndereco);

                    Pessoa pessoa = new Pessoa(idPessoa++, nomePessoa, nascimentoPessoa, cpfPessoa, enderecosPessoa);
                    pessoas.add(pessoa);

                    System.out.println("Pessoa Cadastrada com Sucesso!");

                    break;

                case "2":
                    if (pessoas.isEmpty()){
                        System.out.println("Nenhum Pessoa Cadastrada!");
                    } else {
                        System.out.println("Lista de pessoas cadastradas:\n");
                        for (Pessoa p : pessoas){
                            System.out.println(p);
                            p.imprimeEnderecos();
                            System.out.println("---------------------------------\n");
                        }
                    }
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