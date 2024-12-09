package eng3;

public class Livro {
    Titulo titulo;
    int codigo;
    private boolean disponivel;  // Novo atributo que indica se o livro está disponível
    
    public Livro(int codigo) {
        // Instancia um título e o associa ao livro
        titulo = new Titulo(codigo);
        this.codigo = codigo;
        this.disponivel = true;  // Inicializa o livro como disponível
    }

    public int verPrazo() {
        return titulo.getPrazo();
    }

    public boolean verificaLivro() {
        // Retorna verdadeiro se o código do livro for 3, ou falso caso contrário
        if (this.codigo == 3)
            return true;
        else
            return false;
    }

    public void setEmprestavel(boolean b) {
        // Não há implementação necessária para este método por enquanto
    }

    // Adicionando métodos para controlar a disponibilidade do livro
    public boolean isDisponivel() {
        return disponivel;  // Retorna o estado de disponibilidade do livro
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;  // Modifica o estado de disponibilidade
    }
}
