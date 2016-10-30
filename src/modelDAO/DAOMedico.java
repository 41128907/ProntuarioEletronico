 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelDAO;

import modelConnection.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelBean.BeanMedico;

/**
 *
 * @author carlosantonio
 */
public class DAOMedico {
    ConexaoBD conecta = new ConexaoBD();
    BeanMedico model = new BeanMedico();
    
    public void salvar(BeanMedico mod){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("insert into medicos(nome_medico, crm_medico, especialidade_medico) values(?,?,?)");
            ps.setString(1, mod.getNome());
            ps.setInt(2, mod.getCrm());
            ps.setString(3, mod.getEspecialidade());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir os dados! \n" + ex.getMessage());
        }
        
        conecta.disconnection();
    }
    
    public void Editar(BeanMedico bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("update medicos set nome_medico=?, crm_medico=?, especialidade_medico=? where cod_medico=?");
            ps.setString(1, bm.getNome());
            ps.setInt(2, bm.getCrm());
            ps.setString(3, bm.getEspecialidade());
            ps.setInt(4, bm.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados! \n" + ex.getMessage());
        }
        conecta.disconnection();
    }
    
    public BeanMedico buscaMedico(BeanMedico bm){
        conecta.connection();
        conecta.execulteSQL("select * from medicos where nome_medico like'%" + bm.getPesquisa() + "%'");
        try {
            conecta.rs.first();
            bm.setCodigo(conecta.rs.getInt("cod_medico"));
            bm.setNome(conecta.rs.getString("nome_medico"));
            bm.setCrm(conecta.rs.getInt("crm_medico"));
            bm.setEspecialidade(conecta.rs.getString("especialidade_medico"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado!");
        }
        conecta.disconnection();
        
        return bm;
    }
    
    public void excluir(BeanMedico bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("delete from medicos where cod_medico=?");
            ps.setInt(1, bm.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir os dados! \n" + ex.getMessage());
        }    
        conecta.disconnection();
    }
}
