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
import modelBean.BeanPaciente;

/**
 *
 * @author carlosantonio
 */
public class DAOPaciente {
    ConexaoBD conecta = new ConexaoBD();
    BeanPaciente model = new BeanPaciente();
    
    public void salvar(BeanPaciente mod){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("insert into pacientes(nome_paciente, crm_paciente, especialidade_paciente) values(?,?,?)");
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
    
    public void Editar(BeanPaciente bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("update pacientes set nome_paciente=?, crm_paciente=?, especialidade_paciente=? where cod_paciente=?");
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
    
    public BeanPaciente buscaPaciente(BeanPaciente bm){
        conecta.connection();
        conecta.execulteSQL("select * from pacientes where nome_paciente like'%" + bm.getPesquisa() + "%'");
        try {
            conecta.rs.first();
            bm.setCodigo(conecta.rs.getInt("cod_paciente"));
            bm.setNome(conecta.rs.getString("nome_paciente"));
            bm.setCrm(conecta.rs.getInt("crm_paciente"));
            bm.setEspecialidade(conecta.rs.getString("especialidade_paciente"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado!");
        }
        conecta.disconnection();
        
        return bm;
    }
    
    public void excluir(BeanPaciente bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("delete from pacientes where cod_paciente=?");
            ps.setInt(1, bm.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar excluir os dados! \n" + ex.getMessage());
        }    
        conecta.disconnection();
    }
}
