package repository;

import entity.Pessoa;
import java.util.ArrayList;

public class Repository {
    private ArrayList<Pessoa> pessoas = new ArrayList<>();

    public void adicionar(Pessoa pessoa){ pessoas.add(pessoa); }

    public void remover(Pessoa pessoa){ pessoas.remove(pessoa); }

    public ArrayList<Pessoa> getPessoas(){ return pessoas; }
}
