package eng3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControleTest {

    private Controle controle;

    @BeforeEach
    void setUp() {
        controle = new Controle();
    }

    @Test
    void testDeveRetornarFalsoQuandoAlunoInexistente() {
        String alunoInexistente = "10";
        boolean resultado = controle.emprestar(alunoInexistente, new int[]{7, 14}, 2);
        assertFalse(resultado, "O método deve retornar falso para aluno inexistente com RA '10'");
    }

    @Test
    void testDeveRetornarFalsoQuandoAlunoComDebito() {
        String alunoComDebito = "11";
        boolean resultado = controle.emprestar(alunoComDebito, new int[]{7, 14}, 2);
        assertFalse(resultado, "O método deve retornar falso para aluno com débito com RA '11'");
    }

    @Test
    void testDeveRetornarFalsoQuandoLivrosIndisponiveisParaEmprestimo() {
        String alunoValido = "12"; 
        Livro livro1 = new Livro(30);  // Livro com prazo de 30 dias
        livro1.setDisponivel(false);   // Define o livro como indisponível
        
        int[] prazos = new int[]{30}; // Lista de prazos para o teste
        boolean resultado = controle.emprestar(alunoValido, prazos, 1); // Tentativa de emprestar o livro
        
        assertFalse(resultado, "O método deve retornar falso se o livro não estiver disponível para empréstimo.");
    }


    @Test
    void testDeveRetornarVerdadeiroParaEmprestimoBemSucedido() {
        String alunoValido = "12";
        int[] prazos = new int[]{7, 14};
        boolean resultado = controle.emprestar(alunoValido, prazos, 2);
        assertTrue(resultado, "O método deve retornar verdadeiro para um empréstimo bem-sucedido com RA '12'");
    }

}