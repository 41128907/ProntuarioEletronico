/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelBean.BeanMedico;
import modelBean.BeanUsuario;
import modelConnection.ConexaoBD;

/**
 *
 * @author carlosantonio
 */
public class DAOUsuario {
    ConexaoBD conecta = new ConexaoBD();
    BeanUsuario model = new BeanUsuario();
    
    public void salvar(BeanUsuario mod){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("insert into usuarios(nome_usuario, senha_usuario, tipo_usuario) values(?,?,?)");
            ps.setString(1, mod.getUserName());
            ps.setString(2, mod.getUserPass());
            ps.setString(3, mod.getUserType());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o usuário! \n");
        }
        
        conecta.disconnection();
    }
    
    public void Editar(BeanUsuario bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("update usuarios set nome_usuario=?, senha_usuario=?, tipo_usuario=? where cod_usuario=?");
            ps.setString(1, bm.getUserName());
            ps.setString(2, bm.getUserPass());
            ps.setString(3, bm.getUserType());
            ps.setInt(4, bm.getUserCode());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados! \n");
        }
        conecta.disconnection();
    }
    
    public BeanUsuario buscaUsuario(BeanUsuario bu){
        conecta.connection();
        conecta.execulteSQL("select * from usuarios where nome_usuario like'%" + bu.getUserSearch() + "%'");
        try {
            conecta.rs.first();
            bu.setUserCode(conecta.rs.getInt("cod_usuario"));
            bu.setUserName(conecta.rs.getString("nome_usuario"));
            bu.setUserPass(conecta.rs.getString("senha_usuario"));
            bu.setUserType(conecta.rs.getString("tipo_usuario"));
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
        }
        conecta.disconnection();
        
        return bu;
    }
    
    
    public void excluir(BeanUsuario bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("delete from usuarios where cod_usuario=?");
            ps.setInt(1, bm.getUserCode());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir os dados! \n" );
        }    
        conecta.disconnection();
    }
}
