
package Controller;

import DAO.Conexao;
import DAO.UsuarioDAO;
import Model.Usuario;
import View.LoginView;
import View.MenuView;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public void autenticar() throws SQLException {
        String usuarionome = view.getInputUsuario().getText();
        String senha = view.getjPasswordField1().getText();
        
        Usuario usuarioAutenticar = new Usuario(usuarionome,senha);
        
        Connection conexao = new Conexao().getConnection();
        UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
        
        boolean exist = usuarioDao.existePorNomeESenha(usuarioAutenticar);
        if(exist){
        MenuView telaMenu = new MenuView();
        telaMenu.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(null,"Usuário ou senha inválidos!");
        }
    }
    
    
}
