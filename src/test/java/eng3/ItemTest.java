package eng3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

public class ItemTest {

    private Item item1, item2, item3;

    @Before
    public void setUp() {
        Livro livro1 = new Livro(5); // Prazo de 5 dias
        Livro livro2 = new Livro(10); // Prazo de 10 dias
        Livro livro3 = new Livro(7); // Prazo de 7 dias
        item1 = new Item(livro1);
        item2 = new Item(livro2);
        item3 = new Item(livro3);
    }

    @Test
    public void testCalculoDataDevolucaoAteDoisLivros() {
        // Configuração para testar até 2 livros
        Date hoje = new Date();
        Date dataDevolucao = item1.calculaDataDevolucao(hoje);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoje);
        calendar.add(Calendar.DATE, 5); // Prazo do livro1

        assertEquals(calendar.getTime(), dataDevolucao);
    }

    @Test
    public void testCalculoDataDevolucaoMaisDeDoisLivros() {
        // Configuração para testar mais de 2 livros
        Date hoje = new Date();
        Date dataDevolucao = item3.calculaDataDevolucao(hoje);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hoje);
        calendar.add(Calendar.DATE, 7 + 2); // Prazo do livro3 com 2 dias extras

        assertEquals(calendar.getTime(), dataDevolucao);
    }

    @Test
    public void testCalculoComPrazoInvalido() {
        // Testa livro com prazo inválido
        Livro livroInvalido = new Livro(0);
        Item itemInvalido = new Item(livroInvalido);
        Date hoje = new Date();

        try {
            itemInvalido.calculaDataDevolucao(hoje);
            fail("Deveria lançar exceção para prazo inválido");
        } catch (IllegalArgumentException e) {
            assertEquals("Prazo inválido", e.getMessage());
        }
    }

    
    
    
    @Test
    public void testNenhumLivroEmprestado() {
        // Testa o caso de nenhum livro
        Item itemSemLivro = new Item(null);

        try {
            itemSemLivro.calculaDataDevolucao(new Date());
            fail("Deveria lançar exceção para item sem livro");
        } catch (NullPointerException e) {
            assertNotNull(e.getMessage());
        }
    }
}
