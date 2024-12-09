package eng3;

import java.util.ArrayList;
import java.util.List;

public class Controle {

    public boolean emprestar(String aluno, int[] prazos, int num) {
        Aluno a = new Aluno(aluno);

        if (!verificarAluno(a)) {
            return false;
        }

        if (!verificarDebito(a)) {
            return false;
        }

        List<Livro> livros = obterLivrosDisponiveis(prazos, num);

        if (livros.isEmpty()) {
            return false;
        }

        return processarEmprestimo(a, livros);
    }

    private boolean verificarAluno(Aluno aluno) {
        if (!aluno.verficaAluno()) {
            System.out.println("Aluno Inexistente");
            return false;
        }
        return true;
    }

    private boolean verificarDebito(Aluno aluno) {
        if (!aluno.verificaDebito()) {
            System.out.println("Aluno em Debito");
            return false;
        }
        return true;
    }

    private List<Livro> obterLivrosDisponiveis(int[] prazos, int num) {
        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Livro livro = new Livro(prazos[i]);
            if (!livro.verificaLivro()) {
                livros.add(livro);
            }
        }
        return livros;
    }

    private boolean processarEmprestimo(Aluno aluno, List<Livro> livros) {
        return aluno.emprestar(livros);
    }
}
