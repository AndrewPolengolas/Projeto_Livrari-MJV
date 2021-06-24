package models;

public class Livro extends Produto {
	
	private String titulo;
	private String descricao;
	private Cadastro editora;
	private String autor;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Cadastro getEditora() {
		return editora;
	}

	public void setEditora(Cadastro editora) {
		this.editora = editora;
	}

	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
}
