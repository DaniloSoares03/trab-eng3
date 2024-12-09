## Test Smells

### ControleTest

### Valores fixos

```java 
@Test
public void testEmprestarAlunoInexistente() {
    boolean resultado = controle.emprestar("AlunoInexistente", new int[]{5, 10}, 2);
    assertFalse("Deve retornar false quando o aluno não existe", resultado);
}

```


### Mensagem de erro pouco descritiva

```java

@Test
public void testEmprestarLivrosIndisponiveis() {
    boolean resultado = controle.emprestar("AlunoValido", new int[]{0, 0}, 2);
    assertFalse("Deve retornar false quando todos os livros são exemplares não emprestáveis", resultado);
}

```

## EmprestimoTest

### Falta de isolamento

```java
@Test
public void testEmprestimoComUmLivro() {
    livros.add(livro1); // Altera o estado da lista compartilhada
    boolean resultado = aluno.emprestar(livros); // Usa o estado alterado
    assertTrue(resultado);
}
```

```java
@Test
public void testEmprestimoComMultiplosLivros() {
    livros.add(livro1); // Adiciona livro1 à lista
    livros.add(livro2); // Adiciona livro2 à lista
    boolean resultado = aluno.emprestar(livros); // Usa a lista modificada
    assertTrue(resultado);
}
```

## Cenário generico demais

```java
@Test
public void testEmprestimoComUmLivro() {
    livros.add(livro1); // Adiciona apenas um livro
    boolean resultado = aluno.emprestar(livros); // Testa o empréstimo
    assertTrue(resultado);
}
```

## ItemTest

### Valor fixos

```java
@Test
public void testCalculoDataDevolucaoAteDoisLivros() {
    Date hoje = new Date();
    Date dataDevolucao = item1.calculaDataDevolucao(hoje);
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(hoje);
    calendar.add(Calendar.DATE, 5); // Prazo fixo do livro1
    assertEquals(calendar.getTime(), dataDevolucao);
}
```

### Lançamento de exceção especifica

```java
@Test
public void testCalculoComPrazoInvalido() {
    Livro livroInvalido = new Livro(0);
    Item itemInvalido = new Item(livroInvalido);
    Date hoje = new Date();
    try {
        itemInvalido.calculaDataDevolucao(hoje);
        fail("Deveria lançar exceção para prazo inválido");
    } catch (IllegalArgumentException e) {
        assertEquals("Prazo inválido", e.getMessage()); // Dependência de mensagem específica
    }
}
```

```java
@Test
public void testNenhumLivroEmprestado() {
    Item itemSemLivro = new Item(null);
    try {
        itemSemLivro.calculaDataDevolucao(new Date());
        fail("Deveria lançar exceção para item sem livro");
    } catch (NullPointerException e) {
        assertNotNull(e.getMessage()); // Depende da presença de uma mensagem na exceção
    }
}
```
