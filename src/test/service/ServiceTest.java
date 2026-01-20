package test.service;

import entity.Pessoa;
import entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.Repository;
import service.Service;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ServiceTest {
    private  Repository repository;
    private Service service;

    @BeforeEach
    void setUp() {
        repository = new Repository();
        service = new Service(repository);
    }

    @Test
    @DisplayName("Deve cadastrar usúario no repositorio quando dados válidos forem fornecidos")

    void deveCadastrarPessoaNoRepositorioQuandoDadosValidosForemFornecidos() {
        Endereco e = new Endereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);

        assertEquals("José Silva", repository.getPessoas().get(0).getNome(), "Repositório deve conter a pessoa José Silva após a criação");

    }

    @Test
    @DisplayName("Deve criar endereço quando dados válidos forem fornecidos")

    void deveCriarEnderecoQuandoDadosValidosForemFornecidos() {
        Endereco e = new Endereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");

        assertNotNull(e, "Objeto endereço não pode ser nulo");

    }

    @Test
    @DisplayName("Deve retornar true quando o cpf já for cadastrado")
    void deveRetornarTrueQuandoCpfJaForCadastrado(){
        Endereco e = new Endereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);
        boolean retorno = Service.cpfJaCadastrado("00985497077");

        assertTrue(retorno, "CPF já cadastrado deve deve retornar true");

    }

    @Test
    @DisplayName("Deve imprimir mensagem quando não houver pessoas cadastradas")
    void deveRetornarMensagemQuandoNaoHouverPessoasCadastradas() {
        // Redireciona System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        service.listarPessoas();

        String saida = outContent.toString().trim();
        assertTrue(saida.contains("Nenhum Pessoa Cadastrada!"), "Deve retornar a mensagem de Nenhuma Pessoa Cadastrada quando a lista está vazia");
    }

    @Test
    @DisplayName("Deve imprimir dados das pessoas quando houver cadastro")
    void deveRetornarDadosDoUsuarioQuandoHouverPessoasCadastradas() {
        Endereco e = new Endereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);

        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        service.listarPessoas();

        String saida = outContent.toString();
        assertTrue(saida.contains("José Silva"), "Deve retornar pessoa José Silva na lista de usuários cadastrados");

    }



}
