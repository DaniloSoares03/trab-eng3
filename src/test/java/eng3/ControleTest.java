package eng3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class ControleTest {

    private Controle controle;

    @Before
    public void setUp() {
        controle = new Controle();
    }

    @Test
    public void testEmprestarAlunoInexistente() {
        boolean resultado = controle.emprestar("AlunoInexistente", new int[]{5, 10}, 2);
        assertFalse("Deve retornar false quando o aluno não existe", resultado);
    }

    @Test
    public void testEmprestarAlunoComDebito() {
        boolean resultado = controle.emprestar("AlunoComDebito", new int[]{7, 15}, 2);
        assertFalse("Deve retornar false quando o aluno tem débitos", resultado);
    }

    @Test
    public void testEmprestarComSucesso() {
        boolean resultado = controle.emprestar("AlunoValido", new int[]{5, 7}, 2);
        assertTrue("Deve retornar true quando o empréstimo é bem-sucedido", resultado);
    }

    @Test
    public void testEmprestarSemLivros() {
        boolean resultado = controle.emprestar("AlunoValido", new int[]{}, 0);
        assertFalse("Deve retornar false ao tentar emprestar sem livros", resultado);
    }

    @Test
    public void testEmprestarLivrosIndisponiveis() {
        boolean resultado = controle.emprestar("AlunoValido", new int[]{0, 0}, 2);
        assertFalse("Deve retornar false quando todos os livros são exemplares não emprestáveis", resultado);
    }

    @Test
    public void testEmprestarApenasAlgunsLivrosDisponiveis() {
        boolean resultado = controle.emprestar("AlunoValido", new int[]{5, 0, 7}, 3);
        assertTrue("Deve retornar true quando pelo menos um livro pode ser emprestado", resultado);
    }

    @Test
    public void testEmprestarComLimiteMaximo() {
        boolean resultado = controle.emprestar("AlunoValido", new int[]{5, 5, 5, 5, 5}, 5);
        assertTrue("Deve permitir empréstimo de até 5 livros", resultado);
    }

    @Test
    public void testEmprestarAcimaDoLimite() {
        boolean resultado = controle.emprestar("AlunoValido", new int[]{5, 5, 5, 5, 5, 5}, 6);
        assertFalse("Deve retornar false ao tentar emprestar mais que o limite de livros", resultado);
    }
}
