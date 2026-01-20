package controller;

import service.Service;
import entity.Pessoa;
import entity.Endereco;


import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;


public class Controller {
    Scanner scanner = new Scanner(System.in);
    private final Service service;

    public Controller(Service service) { this.service = service; }

    public static boolean inputValido(String input) {
        return input != null &&
                input.matches("^[12]$");
    }

    public static boolean inputIdValido(String id) {
        return id != null &&
                id.matches("\\d+");
    }

    public void cadastrarPessoa() {
        System.out.println("Digite o nome:");
        String nomePessoa = scanner.nextLine().trim();

        while (!Pessoa.nomeValido(nomePessoa)){
            System.out.println("Nome inválido. Digite novamente:");
            nomePessoa = scanner.nextLine().trim();
        }

        System.out.println("Digite a data de nascimento no formato aaaa-mm-dd:");
        String dataNascimento = scanner.nextLine().trim();

        while (!Pessoa.dataNascimentoValida(dataNascimento)){
            System.out.println("Data inválida. Digite no formato aaaa-mm-dd e que não seja no futuro:");
            dataNascimento = scanner.nextLine().trim();
        }
        LocalDate nascimentoPessoa = LocalDate.parse(dataNascimento);

        System.out.println("Digite o CPF, apenas numeros:");
        String cpfPessoa = scanner.nextLine().trim();

        while (!Pessoa.cpfValido(cpfPessoa) || Service.cpfJaCadastrado(cpfPessoa)){
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
            String numeroRua = scanner.nextLine().trim();

            while (!Endereco.numeroRuaValida(numeroRua)){
                System.out.println("Número inválido, digite novamente:");
                numeroRua = scanner.nextLine().trim();
            }

            Integer numero = Integer.parseInt(numeroRua);

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

            Endereco endereco = service.criarEndereco(idEndereco++, rua, numero, bairro, cidade, estado, cep);
            enderecosPessoa.add(endereco);

            System.out.println("\nDeseja adicionar mais um endereço? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            String inputUsuario = scanner.nextLine().trim();

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

        service.cadastrarUsuario(nomePessoa, nascimentoPessoa, cpfPessoa, enderecosPessoa);

        System.out.println("Pessoa Cadastrada com Sucesso!");
    }

    public void listarPessoa(){
        service.listarPessoas();
    }

    public void atualizarPessoa(){
        String nomePessoa;
        LocalDate nascimentoPessoa;
        String cpfPessoa;
        ArrayList<Endereco> enderecosPessoa = new ArrayList<>();

        System.out.println("Digite o ID da pessoa que deseja atualizar o cadastro:");
        String idPessoa = scanner.nextLine().trim();

        while (!inputIdValido(idPessoa)){
            System.out.println("ID inválido, digite um ID válido para excluir o contato:");
            idPessoa = scanner.nextLine().trim();
        }

        int id = Integer.parseInt(idPessoa);

        Pessoa p = service.buscarPessoa(id);

        if (p != null) {
            System.out.println("\nDeseja editar o nome? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            String inputUsuario = scanner.nextLine().trim();

            while (!inputValido(inputUsuario)){
                System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                inputUsuario = scanner.nextLine().trim();
            }

            if (inputUsuario.equals("1")){
                System.out.println("Digite o novo nome:");

                nomePessoa = scanner.nextLine().trim();

                while (!Pessoa.nomeValido(nomePessoa)){
                    System.out.println("Nome inválido. Digite novamente:");
                    nomePessoa = scanner.nextLine().trim();
                }

            } else {
                nomePessoa = null;
            }

            System.out.println("\nDeseja editar a data de nascimento? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            inputUsuario = scanner.nextLine().trim();

            while (!inputValido(inputUsuario)){
                System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                inputUsuario = scanner.nextLine().trim();
            }

            if (inputUsuario.equals("1")){
                System.out.println("Digite a nova data de nascimento:");

                String dataNascimento = scanner.nextLine().trim();

                while (!Pessoa.dataNascimentoValida(dataNascimento)){
                    System.out.println("Data inválida. Digite no formato aaaa-mm-dd e que não seja no futuro:");
                    dataNascimento = scanner.nextLine().trim();
                }

                nascimentoPessoa = LocalDate.parse(dataNascimento);

            } else {
                nascimentoPessoa = null;
            }

            System.out.println("\nDeseja editar o CPF? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            inputUsuario = scanner.nextLine().trim();

            while (!inputValido(inputUsuario)){
                System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                inputUsuario = scanner.nextLine().trim();
            }

            if (inputUsuario.equals("1")){
                System.out.println("Digite o novo CPF, apenas numeros:");

                cpfPessoa = scanner.nextLine().trim();

                while (!Pessoa.cpfValido(cpfPessoa) || Service.cpfJaCadastrado(cpfPessoa)){
                    System.out.println("CPF inválido, digite novamente: ");
                    cpfPessoa = scanner.nextLine().trim();
                }

            } else {
                cpfPessoa = null;
            }

            System.out.println("\nDeseja editar o(s) endereço(s)? ");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            inputUsuario = scanner.nextLine().trim();

            while (!inputValido(inputUsuario)){
                System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                inputUsuario = scanner.nextLine().trim();
            }

            if (inputUsuario.equals("1")){
                enderecosPessoa = p.getEnderecos();

                boolean atualizarOutroEndereco = false;
                boolean enderecoEncontrado;

                do {
                    System.out.println("Digite o ID do endereço que deseja atualizar o cadastro:");
                    String idEndereco = scanner.nextLine().trim();

                    while (!inputIdValido(idEndereco)){
                        System.out.println("ID inválido, digite um ID válido para atualizar o endereço:");
                        idEndereco = scanner.nextLine().trim();
                    }

                    id = Integer.parseInt(idEndereco);

                    int posicaoDoEndereco = Endereco.buscarPosicaoDoEndereco(id, enderecosPessoa);

                    if (posicaoDoEndereco != -1){
                        enderecoEncontrado = true;
                        System.out.println("\nDeseja editar a rua? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            System.out.println("Digite a novo nome para rua:");

                            String rua = scanner.nextLine().trim();

                            while (!Endereco.enderecoValido(rua)){
                                System.out.println("Nome de Rua inválido, digite novamente:");
                                rua = scanner.nextLine().trim();
                            }

                            enderecosPessoa.get(posicaoDoEndereco).setRua(rua);

                        }

                        System.out.println("\nDeseja editar o numero da rua? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            System.out.println("Digite o novo numero da rua:");

                            String numeroRua = scanner.nextLine().trim();

                            while (!Endereco.numeroRuaValida(numeroRua)){
                                System.out.println("Número inválido, digite novamente:");
                                numeroRua = scanner.nextLine().trim();
                            }

                            Integer numero = Integer.parseInt(numeroRua);

                            enderecosPessoa.get(posicaoDoEndereco).setNumero(numero);

                        }

                        System.out.println("\nDeseja editar o bairro? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            System.out.println("Digite o novo nome para o bairro:");

                            String bairro = scanner.nextLine().trim();

                            while (!Endereco.enderecoValido(bairro)){
                                System.out.println("Bairro inválido, digite novamente:");
                                bairro = scanner.nextLine().trim();
                            }

                            enderecosPessoa.get(posicaoDoEndereco).setBairro(bairro);

                        }

                        System.out.println("\nDeseja editar a cidade? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            System.out.println("Digite o novo nome para a cidade:");

                            String cidade = scanner.nextLine().trim();

                            while (!Endereco.enderecoValido(cidade)){
                                System.out.println("Cidade inválida, digite novamente:");
                                cidade = scanner.nextLine().trim();
                            }

                            enderecosPessoa.get(posicaoDoEndereco).setCidade(cidade);

                        }

                        System.out.println("\nDeseja editar o estado? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            System.out.println("Digite o novo nome para o estado:");

                            String estado = scanner.nextLine().trim();

                            while (!Endereco.enderecoValido(estado)){
                                System.out.println("Estado inválido, digite novamente:");
                                estado = scanner.nextLine().trim();
                            }

                            enderecosPessoa.get(posicaoDoEndereco).setEstado(estado);

                        }

                        System.out.println("\nDeseja editar o CEP? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            System.out.println("Digite o novo CEP:");

                            String cep = scanner.nextLine().trim();

                            while (!Endereco.cepValido(cep)){
                                System.out.println("CEP inválido, digite novamente:");
                                cep = scanner.nextLine().trim();
                            }

                            enderecosPessoa.get(posicaoDoEndereco).setCep(cep);

                        }

                        System.out.println("\nDeseja atualizar outro endereço? ");
                        System.out.println("1 - Sim");
                        System.out.println("2 - Não");

                        inputUsuario = scanner.nextLine().trim();

                        while (!inputValido(inputUsuario)){
                            System.out.println("\nOpção inválida, digite 1 para sim e 2 para não:");
                            inputUsuario = scanner.nextLine().trim();
                        }

                        if (inputUsuario.equals("1")){
                            atualizarOutroEndereco = true;
                        } else {
                            atualizarOutroEndereco = false;
                        }
                        } else {
                        System.out.println("Id do endereço não encontrado!\n");
                        enderecoEncontrado = false;
                    }
                } while (atualizarOutroEndereco || !enderecoEncontrado);


            }else {
                enderecosPessoa = null;
            }

            service.atualizarPessoa(p.getId(), nomePessoa, nascimentoPessoa, cpfPessoa, enderecosPessoa);

            System.out.println("Pessoa atualizada com sucesso!");

        } else {
            System.out.println("Id não encontrado!");
        }

    }

    public void excluirPessoa(){
        System.out.println("Digite o ID da pessoa que deseja excluir:");
        String idPessoa = scanner.nextLine().trim();

        while (!inputIdValido(idPessoa)){
            System.out.println("ID inválido, digite um ID válido para excluir o contato:");
            idPessoa = scanner.nextLine().trim();
        }

        int id = Integer.parseInt(idPessoa);

        boolean pessoaDeletada = service.excluirPessoa(id);

        if (pessoaDeletada){
            System.out.println("Pessoa excluida com sucesso!");
        } else {
            System.out.println("Id não encontrado!");
        }
    }

    public void mostrarIdadePessoa(){
        System.out.println("Digite o ID da pessoa que deseja mostrar a idade:");
        String idPessoa = scanner.nextLine().trim();

        while (!inputIdValido(idPessoa)){
            System.out.println("ID inválido, digite um ID válido para excluir o contato:");
            idPessoa = scanner.nextLine().trim();
        }

        int id = Integer.parseInt(idPessoa);

        Pessoa p = service.buscarPessoa(id);

        if (p != null){
            System.out.println("A idade do(a) " + p.getNome() + " é: " + p.calcularIdade() + " anos.");
        } else {
            System.out.println("Id não encontrado!");
        }

    }


}
