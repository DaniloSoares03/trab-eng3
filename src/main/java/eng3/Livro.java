package eng3;

public class Livro {
	Titulo titulo;
	int codigo;
	public Livro(int codigo) {
		//inst�ncia um titulo e o associa ao livro
		titulo = new Titulo(codigo);
		this.codigo =codigo;
	}
	public int verPrazo() {
		return titulo.getPrazo();
	}
	
	public boolean verificaLivro() {
		// TODO Auto-generated method stub
		if (this.codigo == 3)
		  return true;
		 else
		  return false;
	}
	public void setEmprestavel(boolean b) {
		// TODO Auto-generated method stub
		
	}
    
	
}

