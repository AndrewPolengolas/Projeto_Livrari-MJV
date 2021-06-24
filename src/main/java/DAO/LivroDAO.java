package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Cadastro;
import models.Livro;
import utils.GerenciarConexao;

public class LivroDAO {
	
	public static boolean salvar(Livro livro){
	
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("INSERT INTO produto(cod_barras, preco, nome, descricao, fk_id_editora) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
 
            instrucaoSQL.setInt(1, livro.getCodigoBarras());
            instrucaoSQL.setDouble(2, livro.getPreco());
            instrucaoSQL.setString(3, livro.getTitulo());
            instrucaoSQL.setString(4, livro.getDescricao());
            instrucaoSQL.setInt(5, livro.getEditora().getId());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                        livro.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao obter o Código do produto.");
                }
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
	
	public static boolean atualizar(Livro livro){
		
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE produto SET nome = ?, preco=? WHERE id =? ");
            
            instrucaoSQL.setString(1, livro.getTitulo());
            instrucaoSQL.setDouble(2, livro.getPreco());
            instrucaoSQL.setInt(3, livro.getId());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
	
	public static boolean excluir(Integer id){
		
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM produto WHERE id = ?");
            
            instrucaoSQL.setInt(1, id);

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
             } catch (SQLException ex) {
             }
        }
        return retorno;
    }
	
	public static ArrayList<Livro> consultarProdutos()
    {
        ResultSet resultLivro = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        int aux = 0;
        
        ArrayList<Livro> listaProdutos = new ArrayList<Livro>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM produto;");

            resultLivro = instrucaoSQL.executeQuery();
            
            while(resultLivro.next())
            {
            	Livro livro = new Livro();
            	livro.setId(resultLivro.getInt("id"));
            	livro.setCodigoBarras(resultLivro.getInt("cod_barras"));
            	livro.setTitulo(resultLivro.getString("nome"));
                livro.setPreco(resultLivro.getDouble("preco"));
                livro.setDescricao(resultLivro.getString("descricao"));
                aux = resultLivro.getInt("fk_id_editora");
                listaProdutos.add(livro);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaProdutos = null;
        } finally{
            try {
                if(resultLivro!=null) {
                    resultLivro.close();        
	                try {
	                	for(Livro lista: listaProdutos) {
	                		ArrayList<Cadastro> editora = EditoraDAO.consultarEditorasID(aux);
	                		lista.setEditora(editora.get(0));
	                	}
	            		
	            	}catch(Exception e) {
	            		System.out.println(e);
	            	}
                }
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                        
              } catch (SQLException ex) {
             }
        }
        
        return listaProdutos;
    }
}


