
package DAO;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Usuario insert(Usuario usuario) throws SQLException{

     String sql = "insert into usuario(usuario,senha) values(?, ?);";
     PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
     statement.setString(1, usuario.getUsuario());
     statement.setString(2, usuario.getSenha());
     statement.execute();
     
        ResultSet generatedKeys = statement.getGeneratedKeys();
            
        if(generatedKeys.next()){
            int id = generatedKeys.getInt("id");
            usuario.setId(id);
        } 
        
           return usuario; 
    
    }
    
    public void update(Usuario usuario) throws SQLException{
     String sql = "update usuario set usuario= ?, senha = ? where id=?";
     PreparedStatement statement = connection.prepareStatement(sql);
     statement.setString(1, usuario.getUsuario());
     statement.setString(2, usuario.getSenha());
     statement.setInt(3, usuario.getId());
     statement.execute();
    }
    
    public void insertOrUpdate(Usuario usuario) throws SQLException{
        if(usuario.getId() > 0){
            update(usuario);
        } else{
            insert(usuario);
        }
    }
    
    public void delete(Usuario usuario) throws SQLException{
        String sql = "Delete from usuario where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();
    }
    
    public ArrayList<Usuario> selectAll() throws SQLException{
        String sql = "Select * from usuario";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        return pesquisa(statement);
    }

    private ArrayList<Usuario> pesquisa(PreparedStatement statement) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String usuario = resultSet.getString("usuario");
            String senha = resultSet.getString("senha");
            
            Usuario usuarioLista = new Usuario(id,usuario,senha);
            usuarios.add(usuarioLista);
            
        }
        
        return usuarios;
    }
    
    public Usuario selectPorId(Usuario usuario) throws SQLException{
        String sql = "Select * from Usuario where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.execute();
        
     return pesquisa(statement).get(0);
        
    }

    public boolean existePorNomeESenha(Usuario usuario) throws SQLException {
       String sql = "Select * from usuario where usuario = ? and senha = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());
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
