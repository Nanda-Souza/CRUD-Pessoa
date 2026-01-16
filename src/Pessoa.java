import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Pessoa {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private ArrayList<Endereco> enderecos = new ArrayList<>();

    public Pessoa(Integer id, String nome, LocalDate dataNascimento, String cpf, ArrayList<Endereco> enderecos) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.enderecos = enderecos;
    }

    public static boolean nomeValido(String nome) {
        return nome != null &&
                nome.matches("[a-zA-ZÀ-ÿ ]+") &&
                nome.length() >= 3;
    }

    public static boolean dataNascimentoValida(String data) {
        try {
            LocalDate nascimento = LocalDate.parse(data);
            return !nascimento.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean cpfValido(String cpf) {
        return cpf != null &&
                cpf.matches("^\\d{11}$");
    }

    public static boolean cpfJaCadastrado(String cpf, ArrayList<Pessoa> pessoas) {

        boolean cpfJaCadastrado = false;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.cpf.equals(cpf)) {
                cpfJaCadastrado = true;
                System.out.println("\nCPF ja cadastrado!\n");
                break;
            }

        }

        return cpfJaCadastrado;
    }

    @Override
    public String toString() {
        return  "Id: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Data de Nascimento: " + dataNascimento + "\n" +
                "CPF: " + cpf + "\n";
    }

    public void imprimeEnderecos() {
        System.out.println("Enderecos:");
        for (Endereco e : this.enderecos) {
            System.out.println(e);
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
