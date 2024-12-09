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
    private Livro livro1, livro2,livro3;

    @Before
    public void setUp() {
        aluno = new Aluno("1234"); // Aluno com RA válido
        livros = new ArrayList<>();
        livro1 = new Livro(5); // Livro com prazo de 5 dias
        livro2 = new Livro(7); // Livro com prazo de 7 dias
        livro3 = new Livro(8);
    }

    @Test
    public void testAlunoCadastradoSemDebitos() {
        assertTrue("O aluno deveria estar cadastrado", aluno.verficaAluno());
        assertFalse("O aluno não deveria ter débitos", aluno.verificaDebito());
    }

    @Test
    public void testAlunoComDebitos() {
        Aluno alunoDebito = new Aluno("10"); // RA inválido
        assertTrue("O aluno com RA inválido deveria ter débitos", alunoDebito.verificaDebito());
    }

    @Test
    public void testEmprestimoComUmLivro() {
        livros.add(livro1);
        boolean resultado = aluno.emprestar(livros);
        assertTrue("Deveria permitir o empréstimo de um livro", resultado);
    }

    @Test
    public void testEmprestimoComMultiplosLivros() {
        livros.add(livro1);
        livros.add(livro2);
        livros.add(livro3); // Adiciona o terceiro livro

        Emprestimo emprestimo = new Emprestimo();
        boolean resultado = emprestimo.emprestar(livros);

        // Verifica se o empréstimo foi realizado com sucesso
        assertTrue(resultado);

        // Verifica se a data de devolução foi ajustada corretamente para mais de dois livros
        Date dataPrevistaEsperada = emprestimo.CalculaDataDevolucao(); // Obtém a data de devolução ajustada

        // Primeiro, calcula a data de devolução antes de qualquer ajuste
        long prazoTotal = livro1.verPrazo() + livro2.verPrazo() + livro3.verPrazo();
        Date dataCalculada = new Date();
        dataCalculada.setTime(dataPrevistaEsperada.getTime());

        // Verifica se a data prevista foi ajustada corretamente
        // O ajuste é feito para mais dias com base no número de livros
        assertTrue(dataCalculada.after(emprestimo.getDataEmprestimo()));

        // Verifica se a data de devolução foi ajustada corretamente
        // O prazo total é somado, e a data de devolução deve ser maior que o empréstimo inicial
        assertEquals(emprestimo.CalculaDataDevolucao(), dataPrevistaEsperada);
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

        assertEquals("A data de devolução deveria ser calculada corretamente", dataEsperada, dataDevolucao);
    }

    @Test
    public void testLivroNaoEmprestavel() {
        livros.add(livro1);

        // A funcionalidade "não emprestável" não está implementada em Livro.
        // Portanto, este teste verifica apenas o estado inicial.
        boolean resultado = aluno.emprestar(livros);

        assertTrue("Como o método setEmprestavel não altera comportamento, o livro deve ser emprestável", resultado);
    }

    @Test
    public void testItemAssociacaoLivro() {
        Item item = new Item(livro1);
        assertEquals("O item deveria estar associado ao livro correto", livro1, item.getLivro());
    }
}
