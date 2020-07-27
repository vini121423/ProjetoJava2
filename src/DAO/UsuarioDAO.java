
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Usuario usuario) throws SQLException{

     String sql = "insert into usuario(usuario,senha) values ('"+ usuario.getUsuario() +"', '"+ usuario.getSenha() +"');";
     PreparedStatement statement = connection.prepareStatement(sql);
     statement.execute();
            
      
            
    
    }

    public boolean existePorNomeESenha(Usuario usuario) throws SQLException {
       String sql = "Select * from usuario where usuario ='"+ usuario.getUsuario()+ "' and senha='"+ usuario.getSenha() +"' ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        
        ResultSet resultSet = statement.getResultSet();
       
        /*
        if(resultSet.next()){
            return true;
        } else{
           return false;
        }
        */
        return resultSet.next();
    }

   
}
