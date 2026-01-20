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
        Endereco e = service.criarEndereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);

        assertEquals("José Silva", repository.getPessoas().get(0).getNome(), "Repositório deve conter a pessoa José Silva após a criação");

    }

    @Test
    @DisplayName("Deve criar endereço quando dados válidos forem fornecidos")

    void deveCriarEnderecoQuandoDadosValidosForemFornecidos() {
        Endereco e = service.criarEndereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
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

    @Test
    @DisplayName("Deve buscar pessoa por id")
    void deveRetornarPessoaBuscandoPorIdQuandoHouverPessoasCadastradas() {
        Endereco e = new Endereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);
        Pessoa p = service.buscarPessoa(1);

        assertNotNull(p);

    }

    @Test
    @DisplayName("Deve alterar os dados do usuario no repositorio quando dados válidos forem fornecidos")

    void deveAlterarDadosdaPessoaNoRepositorioQuandoDadosValidosForemFornecidos() {
        Endereco e = service.criarEndereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);

        Endereco novoE = service.criarEndereco(1, "7 de Setembro", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> novoEndereco = new ArrayList<>();
        novoEndereco.add(novoE);

        boolean retorno = service.atualizarPessoa(1, "João Souza", null,"00985497079", novoEndereco );

        assertTrue(retorno, "Quando o endereço é alterado deve retornar true.");
        assertEquals("João Souza", repository.getPessoas().get(0).getNome(), "Repositório deve conter a pessoa João Souza após a alteração");
        assertEquals("7 de Setembro", repository.getPessoas().get(0).getEnderecos().get(0).getRua(), "Repositório deve conter o endereço 7 de Setembro para pessoa João Souza após a alteração");

    }

    @Test
    @DisplayName("Não Deve alterar os dados do usuario no repositorio quando não encontrar o id da pessoa")

    void naoDeveAlterarDadosdaPessoaNoRepositorioQuandoNaoEncontrarOidDaPessoa() {
        Endereco e = service.criarEndereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);

        Endereco novoE = service.criarEndereco(1, "7 de Setembro", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> novoEndereco = new ArrayList<>();
        novoEndereco.add(novoE);

        boolean retorno = service.atualizarPessoa(2, "João Souza", null,"00985497079", novoEndereco );

        assertFalse(retorno, "Quando o id da pessoa não é localizado endereço deve retornar false.");
        assertEquals("José Silva", repository.getPessoas().get(0).getNome(), "Repositório deve conter a pessoa José Silva pois não teve alteração");
        assertEquals("7 de Abril", repository.getPessoas().get(0).getEnderecos().get(0).getRua(), "Repositório deve conter o endereço 7 de Abril para pessoa José Silva pois não teve alteração");

    }

    @Test
    @DisplayName("Deve excluir o usúario no repositorio quando o id fornecido for válido")

    void deveExcluirPessoaNoRepositorioQuandoDadosIdFornecidoForValido() {
        Endereco e = service.criarEndereco(1, "7 de Abril", 15, "Centro", "Poa", "RS", "91750410");
        ArrayList<Endereco> enderecos = new ArrayList<>();
        enderecos.add(e);
        service.cadastrarUsuario("José Silva", LocalDate.parse("1988-05-05"), "00985497077", enderecos);

        boolean resultado = service.excluirPessoa(1);

        assertTrue(resultado, "Deve retornar True após excluir a pessoa do Repositório");
        assertEquals(0, repository.getPessoas().size(), "Tamanho do repositório deve ser zero após o usuário ser excluido");

    }



}
