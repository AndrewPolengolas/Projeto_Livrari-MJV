package utils;

import java.util.ArrayList;

import DAO.EditoraDAO;
import DAO.LivroDAO;
import models.Cadastro;
import models.Livro;

public class Main {
	public static void main(String[] args){
	
		//LivroDAO.salvar(livro);
		
		/*
		LivroDAO.excluir(1);
		LivroDAO.excluir(2);
		LivroDAO.excluir(3);
		*/
		ArrayList<Cadastro> editoras = EditoraDAO.consultarEditoras();
		
		Cadastro editora = editoras.get(0);
		
		Livro livro = new Livro();
		livro.setTitulo("Percy Jackson");
		livro.setPreco(32.50);
		livro.setAutor("Rick Riordan");
		livro.setCodigoBarras(123456788);
		livro.setDescricao("Um livro de mitologia...");
		livro.setEditora(editora);
		
		//LivroDAO.salvar(livro);
		
		try {
			ArrayList<Livro> livros = LivroDAO.consultarProdutos();
		
			for(Livro lista: livros) {
				System.out.printf("Id Livro: %d\nTitulo: %s\nDescrição: %s\nEditora: %s\nPreço: R$ %.2f\n", lista.getId(), lista.getTitulo(), lista.getDescricao(), lista.getEditora().getNome(), lista.getPreco());
				System.out.println("--------------------------------------");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}
