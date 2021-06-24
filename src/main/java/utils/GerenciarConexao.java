package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andrew do Nascimento
 */
public class GerenciarConexao {
    public static String STATUS = "N�o conectado";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String SERVER = "localhost";
    public static String DATABASE = "livraria";
    public static String LOGIN = "root";
    public static String SENHA = "";
    public static String URL = "";
    public static Connection CONEXAO;

    public GerenciarConexao() {}
    
    /**
     * Metodo que faz a liga��o com o banco de dados
     * @return Retorna um objeto do tipo conex�o
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static Connection abrirConexao() throws ClassNotFoundException,SQLException {
 
        URL = "jdbc:mysql://" + SERVER + ":3306/" +DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
        
        if(CONEXAO==null)      
        {    
            try {

                Class.forName(DRIVER);
                CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);

                if (CONEXAO != null) {
                    STATUS = "Conex�o realizada com sucesso!";
                } else {
                    STATUS = "N�o foi possivel realizar a conex�o";
                }

            } catch (ClassNotFoundException e) {

                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");

            } catch (SQLException e) {

                throw new SQLException("Erro ao estabelecer a conex�o.");
            }
            
        }
        else
        {
            try {
                if(CONEXAO.isClosed())
                    CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conex�o.");
            }
        }
        return CONEXAO;
    }
    
    /**
     * Metodo que interrompe a liga��o com o banco de dados
     * @return Retorna true: caso a conex�o tenha sido fechada, false: caso a conex�o nao possa ser fechada
     * @throws java.sql.SQLException
     */
    public static boolean fecharConexao() throws SQLException {
 
        boolean retorno;
        
        try {
            if(CONEXAO!=null){
                if(!CONEXAO.isClosed())
                    CONEXAO.close();
            }
            
            STATUS = "N�o conectado";
            retorno = true;
            
         } catch (SQLException e) {
            retorno = false;
        }
        
        return retorno;
    }
}
