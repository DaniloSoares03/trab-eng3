package eng3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmprestimoTest {
    
    private Aluno aluno;
    private List<Livro> livros;
    private Livro livro1, livro2;

    @Before
    public void setUp() {
        aluno = new Aluno("1234"); // Aluno com RA válido
        livros = new ArrayList<>();
        livro1 = new Livro(5); // Livro com prazo de 5 dias
        livro2 = new Livro(7); // Livro com prazo de 7 dias
    }

    @Test
    public void testAlunoCadastradoSemDebitos() {
        assertTrue(aluno.verficaAluno()); // Verifica que o aluno está cadastrado
        assertFalse(aluno.verificaDebito()); // Verifica que o aluno não tem débitos
    }

    @Test
    public void testAlunoComDebitos() {
        Aluno alunoDebito = new Aluno("10"); // RA inválido
        assertTrue(alunoDebito.verificaDebito()); // Verifica que o aluno tem débitos
    }

    @Test
    public void testEmprestimoComUmLivro() {
        livros.add(livro1);
        boolean resultado = aluno.emprestar(livros);
        assertTrue(resultado);
    }

    @Test
    public void testEmprestimoComMultiplosLivros() {
        livros.add(livro1);
        livros.add(livro2);
        boolean resultado = aluno.emprestar(livros);
        assertTrue(resultado);
    }

    
    
    @Test
    public void testCalculoDataDeDevolucao() {
        Item item = new Item(livro1);
        Date hoje = new Date();
        Date dataDevolucao = item.calculaDataDevolucao(hoje);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoje);
        calendar.add(Calendar.DATE, livro1.verPrazo());
        Date dataEsperada = calendar.getTime();

        assertEquals(dataEsperada, dataDevolucao);
    }

    @Test
    public void testLivroNaoEmprestavel() {
        livro1.setEmprestavel(false);
        livros.add(livro1);
        boolean resultado = aluno.emprestar(livros);
        assertFalse(resultado);
    }

    @Test
    public void testItemAssociacaoLivro() {
        Item item = new Item(livro1);
        assertEquals(livro1, item.getLivro());
    }
}
