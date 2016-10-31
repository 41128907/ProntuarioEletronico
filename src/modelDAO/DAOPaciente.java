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
            PreparedStatement ps = conecta.conn.prepareStatement("insert into pacientes(nome_paciente, nasc_paciente, rg_paciente, tel_paciente, rua_paciente, cep_paciente,complemento_paciente, bairro_paciente) values(?,?,?,?,?,?,?,?)");
            ps.setString(1, mod.getNome());
            ps.setString(2, mod.getNascimento());
            ps.setString(3, mod.getRg());
            ps.setString(4, mod.getTelefone());
            ps.setString(5, mod.getRua());
            ps.setString(6, mod.getCep());
            ps.setString(7, mod.getComplemento());
            ps.setString(8, mod.getBairro());
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
            PreparedStatement ps = conecta.conn.prepareStatement("update pacientes set nome_paciente=?,nasc_paciente=?,rg_paciente=?,tel_paciente=?,rua_paciente=?,cep_paciente=?,complemento_paciente=?,bairro_paciente=? where cod_paciente=?");
            ps.setString(1, bm.getNome());
            ps.setString(2, bm.getNascimento());
            ps.setString(3, bm.getRg());
            ps.setString(4, bm.getTelefone());
            ps.setString(5, bm.getRua());
            ps.setString(6, bm.getCep());
            ps.setString(7, bm.getComplemento());
            ps.setString(8, bm.getBairro());
            ps.setInt(9, bm.getCodigo());
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
            bm.setNascimento(conecta.rs.getString("nasc_paciente"));
            bm.setRg(conecta.rs.getString("rg_paciente"));
            bm.setTelefone(conecta.rs.getString("tel_paciente"));
            bm.setRua(conecta.rs.getString("rua_paciente"));
            bm.setCep(conecta.rs.getString("cep_paciente"));
            bm.setComplemento(conecta.rs.getString("complemento_paciente"));
            bm.setBairro(conecta.rs.getString("bairro_paciente"));
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
