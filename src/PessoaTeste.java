import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PessoaTeste {

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


}
