package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Cadastro;
import models.Endereco;
import utils.GerenciarConexao;

public class EditoraDAO {
	public static boolean salvar(Cadastro editora){
			
	        boolean retorno = false;
	        Connection conexao = null;
	        PreparedStatement instrucaoSQL = null;
	                
	        try {
	            conexao = GerenciarConexao.abrirConexao();
	
	            instrucaoSQL = conexao.prepareStatement("INSERT INTO editora(nome, telefone, email, cnpj, fk_id_endereco) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
	 
	            instrucaoSQL.setString(1, editora.getNome());
	            instrucaoSQL.setLong(2, editora.getTelefone());
	            instrucaoSQL.setString(3, editora.getEmail());
	            instrucaoSQL.setString(4, editora.getCpfCnpj());
	            instrucaoSQL.setInt(5, editora.getEndereco().getId());
	            
	            int linhasAfetadas = instrucaoSQL.executeUpdate();
	            
	            if(linhasAfetadas>0)
	            {
	                retorno = true;
	                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
	                if (generatedKeys.next()) {
	                        editora.setId(generatedKeys.getInt(1));
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
	
	public static ArrayList<Cadastro> consultarEditoras(){
        ResultSet resultEditora = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        int aux = 0;
        
        ArrayList<Cadastro> listaEditoras = new ArrayList<Cadastro>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM editora;");

            resultEditora = instrucaoSQL.executeQuery();
            
            while(resultEditora.next())
            {
            	Cadastro editora = new Cadastro();
            	editora.setId(resultEditora.getInt("id"));
            	editora.setNome(resultEditora.getString("nome"));
            	editora.setTelefone(resultEditora.getLong("telefone"));
            	editora.setEmail(resultEditora.getString("email"));
            	editora.setCpfCnpj(resultEditora.getString("cnpj"));
            	aux = resultEditora.getInt("fk_id_endereco");
            	listaEditoras.add(editora);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaEditoras = null;
        } finally{
            try {
                if(resultEditora!=null) {
                    resultEditora.close();  
	                try {
	            		ArrayList<Endereco> endereco = EnderecoDAO.consultarEnderecosID(aux);
	            		listaEditoras.get(0).setEndereco(endereco.get(0));
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
        
        return listaEditoras;
    }
	
	public static ArrayList<Cadastro> consultarEditorasID(Integer id){
        ResultSet resultEditora = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        int aux = 0;
        
        ArrayList<Cadastro> listaEditoras = new ArrayList<Cadastro>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from editora where id = ?;");
            
            instrucaoSQL.setInt(1, id);

            resultEditora = instrucaoSQL.executeQuery();
            
            while(resultEditora.next())
            {
            	Cadastro editora = new Cadastro();
            	editora.setId(resultEditora.getInt("id"));
            	editora.setNome(resultEditora.getString("nome"));
            	editora.setTelefone(resultEditora.getLong("telefone"));
            	editora.setEmail(resultEditora.getString("email"));
            	editora.setCpfCnpj(resultEditora.getString("cnpj"));
            	aux = resultEditora.getInt("fk_id_endereco");
            	
            	listaEditoras.add(editora);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaEditoras = null;
        } finally{
            try {
                if(resultEditora!=null) {
                	resultEditora.close();
                	try {
                		ArrayList<Endereco> endereco = EnderecoDAO.consultarEnderecosID(aux);
                		listaEditoras.get(0).setEndereco(endereco.get(0));
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
        
        return listaEditoras;
    }
}
