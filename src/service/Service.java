package service;

import repository.Repository;
import entity.Endereco;
import entity.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;

public class Service {
    private Repository pessoas;
    private int idPessoa = 1;

    public Service(Repository pessoas) {
        this.pessoas = pessoas;
    }

    public void cadastrarUsuario(String nome, LocalDate dataNascimento, String cpf, ArrayList<Endereco> enderecos) {
        Pessoa p = new Pessoa(idPessoa++, nome, dataNascimento, cpf, enderecos);
        pessoas.adicionar(p);

    }

    public boolean cpfJaCadastrado(String cpf) {

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



}
