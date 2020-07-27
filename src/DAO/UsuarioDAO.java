
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            
      connection.close();
            
    
    }
}
