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

            Endereco endereco = service.criarEndereco(idEndereco, rua, numero, bairro, cidade, estado, cep);
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

}
