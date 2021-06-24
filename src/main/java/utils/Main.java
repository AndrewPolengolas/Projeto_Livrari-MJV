package utils;

import java.util.ArrayList;

import DAO.LivroDAO;
import models.Livro;

public class Main {
	public static void main(String[] args){
		
		Livro livro = new Livro();
		livro.setTitulo("Harry Potter");
		livro.setPreco(29.99);
		
		//LivroDAO.salvar(livro);
		
		/*
		LivroDAO.excluir(1);
		LivroDAO.excluir(2);
		LivroDAO.excluir(3);
		*/
		
		try {
			ArrayList<Livro> livros = LivroDAO.consultarProdutos();
		
			for(Livro lista: livros) {
				System.out.printf("Id Livro: %d\nTitulo: %s\nPreço: R$ %.2f\n", lista.getId(), lista.getTitulo(), lista.getPreco());
				System.out.println("----------------------------");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}
