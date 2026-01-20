package test.entity;

import entity.Pessoa;
import entity.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EntityTest {
    //Testes para as validações da entity Pessoa
    @Test
    @DisplayName("Deve retornar falso quando nome contiver dígitos/somente números")
    void deveRetornarFalsoQuandoNomeForInvalido(){
        String nome = "123";
        boolean retorno = Pessoa.nomeValido(nome);

        assertFalse(retorno, "Nome com dígitos não é um nome válido");

    }

    @Test
    @DisplayName("Deve retornar falso quando nome for nulo")
    void deveRetornarFalsoQuandoNomeForNulo(){
        String nome = null;
        boolean retorno = Pessoa.nomeValido(nome);

        assertFalse(retorno, "Nome nulo não é um nome válido");

    }

    @Test
    @DisplayName("Deve retornar falso quando nome tiver menos de 3 caracteres")
    void deveRetornarFalsoQuandoNomeTiverMenosDeTresCaracteres(){
        String nome = "An";
        boolean retorno = Pessoa.nomeValido(nome);

        assertFalse(retorno, "Nome com menos de 3 caracteres não é um nome válido");

    }

    @Test
    @DisplayName("Deve retornar true quando nome for válido")
    void deveRetornarTrueQuandoNomeForValido(){
        String nome = "Ana";
        boolean retorno = Pessoa.nomeValido(nome);

        assertTrue(retorno, "Um nome com mais de 3 caracteres e sem numeros é um nome válido");

    }

    @Test
    @DisplayName("Deve retornar false quando a data estiver no formato errado")
    void deveRetornarFalseQuandoDataDeNascimentoForInvalida(){
        String dataNascimento = "1990/09/30";
        boolean retorno = Pessoa.dataNascimentoValida(dataNascimento);

        assertFalse(retorno, "Data de nascimento com formato diferente de aaaa-mm-dd não é válida");

    }

    @Test
    @DisplayName("Deve retornar false quando a data for no futuro em relação ao dia corrente")
    void deveRetornarFalseQuandoDataDeNascimentoForNoFuturo(){
        String dataNascimento = "2030/09/30";
        boolean retorno = Pessoa.dataNascimentoValida(dataNascimento);

        assertFalse(retorno, "Uma data no futuro do dia corrente não é válida");

    }

    @Test
    @DisplayName("Deve retornar false quando a data for no futuro em relação ao dia corrente")
    void deveRetornarTrueQuandoDataDeNascimentoForValida(){
        String dataNascimento = "1990-09-30";
        boolean retorno = Pessoa.dataNascimentoValida(dataNascimento);

        assertTrue(retorno, "Data de nascimento no formato aaaa-mm-dd deve ser válida");

    }

    @Test
    @DisplayName("Deve retornar false quando o cpf for inválido")
    void deveRetornarFalseQuandoCpfForInvalido(){
        String cpf = "abcdefg";
        boolean retorno = Pessoa.cpfValido(cpf);

        assertFalse(retorno, "CPF contendo letras deve ser inválido");

    }

    @Test
    @DisplayName("Deve retornar true quando o cpf for válido")
    void deveRetornarTrueQuandoCpfForValido(){
        String cpf = "02314216050";
        boolean retorno = Pessoa.cpfValido(cpf);

        assertTrue(retorno, "CPF com 11 dígitos deve ser válido");

    }

    //Testes para as validações da entity Endereco
    @Test
    @DisplayName("Deve retornar false quando o número da rua conter caracteres")
    void deveRetornarFalseQuandoNumeroDaRuaForInvalido(){
        String numeroRua = "abcde";
        boolean retorno = Endereco.numeroRuaValida(numeroRua);

        assertFalse(retorno, "Numero da rua contendo caracteres especiais deve ser inválido");

    }

    @Test
    @DisplayName("Deve retornar true quando o número da rua conter somente números")
    void deveRetornarTrueQuandoNumeroDaRuaForValido(){
        String numeroRua = "123";
        boolean retorno = Endereco.numeroRuaValida(numeroRua);

        assertTrue(retorno, "Numero da rua contendo caracteres especiais deve ser inválido");

    }

    @Test
    @DisplayName("Deve retornar false quando o endereço conter somente números")
    void deveRetornarFalseQuandoEnderecoForInvalido(){
        String endereco = "12345";
        boolean retorno = Endereco.enderecoValido(endereco);

        assertFalse(retorno, "Endereço contendo somente numeros deve ser inválido");

    }

    @Test
    @DisplayName("Deve retornar false quando o endereço conter menos de dois caracteres")
    void deveRetornarFalseQuandoEnderecoTiverMenosDeDoisCaracteres(){
        String endereco = "A";
        boolean retorno = Endereco.enderecoValido(endereco);

        assertFalse(retorno, "Endereço contendo somente um caracter deve ser inválido");

    }

    @Test
    @DisplayName("Deve retornar true quando o endereço conter letras ou uma combinação de letras e números")
    void deveRetornarTrueQuandoEnderecoForValido(){
        String endereco = "Rua 7 de Abril";
        boolean retorno = Endereco.enderecoValido(endereco);

        assertTrue(retorno, "Endereço contendo somente letras ou uma combinação de letras e númemros deve ser válido");

    }

    @Test
    @DisplayName("Deve retornar false quando o cep for inválido")
    void deveRetornarFaqlseQuandoCepForInvalido(){
        String cep = "abcdef";
        boolean retorno = Endereco.cepValido(cep);

        assertFalse(retorno, "CEP contendo letras deve ser inválido");

    }

    @Test
    @DisplayName("Deve retornar true quando o cep for válido")
    void deveRetornarTrueQuandoCepForValido(){
        String cep = "91760655";
        boolean retorno = Endereco.cepValido(cep);

        assertTrue(retorno, "CEP com 8 dígitos deve ser válido");

    }



}
