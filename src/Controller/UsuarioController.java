
package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.FormCadastroView;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UsuarioController {
    private FormCadastroView view;

    public UsuarioController(FormCadastroView view) {
        this.view = view;
    }
    
    public void salvarUsuario(){
        String nomeusuario = view.getjTextField1().getText();
        String senha = view.getjPasswordField1().getText();
        
        Usuario usuario = new Usuario(nomeusuario,senha);
        
         
        try {
            Connection conexao = new Conexao().getConnection();
            UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
            usuarioDao.insert(usuario);
            
            JOptionPane.showMessageDialog(null,"Usuário salvo com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(FormCadastroView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
