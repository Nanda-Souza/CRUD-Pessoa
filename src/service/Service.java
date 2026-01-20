package service;

import repository.Repository;
import entity.Endereco;
import entity.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;

public class Service {
    private static Repository pessoas;
    private int idPessoa = 1;

    public Service(Repository pessoas) {
        this.pessoas = pessoas;
    }

    public void cadastrarUsuario(String nome, LocalDate dataNascimento, String cpf, ArrayList<Endereco> enderecos) {
        Pessoa p = new Pessoa(idPessoa++, nome, dataNascimento, cpf, enderecos);
        pessoas.adicionar(p);

    }

    public static boolean cpfJaCadastrado(String cpf) {

        boolean cpfJaCadastrado = false;

        for (Pessoa p : pessoas.getPessoas()) {
            if (p.getCpf().equals(cpf)) {
                cpfJaCadastrado = true;
                System.out.println("\nCPF ja cadastrado!\n");
                break;
            }

        }

        return cpfJaCadastrado;
    }

    public Endereco criarEndereco(int idEndereco, String rua, int numero, String bairro, String cidade, String estado, String cep) {

        return new Endereco(idEndereco, rua, numero, bairro, cidade, estado, cep);

    }

    public void listarPessoas(){
        if (pessoas.getPessoas().isEmpty()){
            System.out.println("Nenhum Pessoa Cadastrada!");
        } else {
            for (Pessoa p : pessoas.getPessoas()) {
                System.out.println(p);
                p.imprimeEnderecos();
                System.out.println("---------------------------------\n");
            }
        }
    }

    public Pessoa buscarPessoa(int idPessoa){
        for (Pessoa p : pessoas.getPessoas()) {
            if (p.getId() == idPessoa) {
                return p;
            }
        }

        return null;
    }

    public boolean atualizarPessoa(int idPessoa, String novoNome, LocalDate novaDataNascimento, String novoCpf, ArrayList<Endereco> novosEnderecos) {
        Pessoa p = buscarPessoa(idPessoa);

        if (p == null) {
            return false;
        }

        if (novoNome != null) {
            p.setNome(novoNome);
        }
        if (novaDataNascimento != null) {
            p.setDataNascimento(novaDataNascimento);
        }
        if (novoCpf != null) {
            p.setCpf(novoCpf);
        }
        if (novosEnderecos != null) {
            p.setEnderecos(novosEnderecos);
        }

        return true;
    }



}
