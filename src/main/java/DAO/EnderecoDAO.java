package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Endereco;
import utils.GerenciarConexao;

public class EnderecoDAO {
public static boolean salvar(Endereco endereco){
		
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("INSERT INTO endereco(logradouro, numero, bairro, cidade, uf) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
 
            instrucaoSQL.setString(1, endereco.getLogradouro());
            instrucaoSQL.setString(2, endereco.getNumero());
            instrucaoSQL.setString(3, endereco.getBairro());
            instrucaoSQL.setString(4, endereco.getCidade());
            instrucaoSQL.setString(5, endereco.getEstado());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                        endereco.setId(generatedKeys.getInt(1));
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

	public static ArrayList<Endereco> consultarEnderecos(){
	    ResultSet resultEndereco = null;
	    Connection conexao = null;
	    PreparedStatement instrucaoSQL = null; 
	    
	    ArrayList<Endereco> listaEnderecos = new ArrayList<Endereco>();
	    
	    try {
	        
	        conexao = GerenciarConexao.abrirConexao();
	        instrucaoSQL = conexao.prepareStatement("SELECT * FROM endereco;");
	
	        resultEndereco = instrucaoSQL.executeQuery();
	        
	        while(resultEndereco.next())
	        {
	        	Endereco endereco = new Endereco();
	        	endereco.setId(resultEndereco.getInt("id"));
	        	endereco.setLogradouro(resultEndereco.getString("logradouro"));
	        	endereco.setNumero(resultEndereco.getString("numero"));
	        	endereco.setBairro(resultEndereco.getString("bairro"));
	        	endereco.setCidade(resultEndereco.getString("cidade"));
	        	endereco.setEstado(resultEndereco.getString("uf"));
	        	listaEnderecos.add(endereco);
	        }
	        
	    }catch (SQLException | ClassNotFoundException ex) {
	        System.out.println(ex.getMessage());
	        listaEnderecos = null;
	    } finally{
	        try {
	            if(resultEndereco!=null)
	                resultEndereco.close();                
	            if(instrucaoSQL!=null)
	                instrucaoSQL.close();
	            
	            GerenciarConexao.fecharConexao();
	                    
	          } catch (SQLException ex) {
	         }
	    }
	    
	    return listaEnderecos;
	}
	
	public static ArrayList<Endereco> consultarEnderecosID(Integer id){
	    ResultSet resultEndereco = null;
	    Connection conexao = null;
	    PreparedStatement instrucaoSQL = null; 
	    
	    ArrayList<Endereco> listaEnderecos = new ArrayList<Endereco>();
	    
	    try {
	        
	        conexao = GerenciarConexao.abrirConexao();
	        instrucaoSQL = conexao.prepareStatement("SELECT * FROM endereco where id = ?;");
	        
	        instrucaoSQL.setInt(1, id);
	
	        resultEndereco = instrucaoSQL.executeQuery();
	        
	        while(resultEndereco.next())
	        {
	        	Endereco endereco = new Endereco();
	        	endereco.setId(resultEndereco.getInt("id"));
	        	endereco.setLogradouro(resultEndereco.getString("logradouro"));
	        	endereco.setNumero(resultEndereco.getString("numero"));
	        	endereco.setBairro(resultEndereco.getString("bairro"));
	        	endereco.setCidade(resultEndereco.getString("cidade"));
	        	endereco.setEstado(resultEndereco.getString("uf"));
	        	listaEnderecos.add(endereco);
	        }
	        
	    }catch (SQLException | ClassNotFoundException ex) {
	        System.out.println(ex.getMessage());
	        listaEnderecos = null;
	    } finally{
	        try {
	            if(resultEndereco!=null)
	                resultEndereco.close();                
	            if(instrucaoSQL!=null)
	                instrucaoSQL.close();
	            
	            GerenciarConexao.fecharConexao();
	                    
	          } catch (SQLException ex) {
	         }
	    }
	    
	    return listaEnderecos;
	}
}
