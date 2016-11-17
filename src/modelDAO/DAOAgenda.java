/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelBean.BeanAgenda;
import modelBean.BeanMedico;
import modelBean.BeanUsuario;
import modelConnection.ConexaoBD;

/**
 *
 * @author carlosantonio
 */
public class DAOAgenda {
    ConexaoBD conecta = new ConexaoBD();
    BeanAgenda model = new BeanAgenda();
    ConexaoBD conecta_paciente = new ConexaoBD();
    ConexaoBD conecta_medico = new ConexaoBD();
    
    int cod_med=1;
    int cod_pac;
    
    public void salvar(BeanAgenda agenda){
        
        buscaMedico(agenda.getNome_medico());
        
        buscaPaciente(agenda.getNome_paciente());
        
        conecta.connection();
        
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("insert into agenda(agenda_codpac, agenda_codmed, agenda_motivo, agenda_turno, agenda_data, agenda_status) values(?,?,?,?,?,?)");
            ps.setInt(1, cod_pac);
            ps.setInt(2, cod_med);
            ps.setString(3, agenda.getMotivo());
            ps.setString(4, agenda.getTurno());
            ps.setDate(5, new java.sql.Date(agenda.getData().getTime() ));
            ps.setString(6, agenda.getStatus());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Agendamento feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar fazer o agendamento! \n" + ex);
        }
        
        conecta.disconnection();
    }
    
    
    public void buscaMedico(String nomeMedico){
        conecta_medico.connection();
        conecta_medico.execulteSQL("select * from medicos where nome_medico='" + nomeMedico  + "'");
        try {
            conecta_medico.rs.first();
            
            cod_med = conecta_medico.rs.getInt("cod_medico");
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado!" + ex);
        }
        
        
    }
    
    public int buscaCodMedico(String nomeMedico){
        conecta_medico.connection();
        conecta_medico.execulteSQL("select * from medicos where nome_medico='" + nomeMedico  + "'");
        try {
            conecta_medico.rs.first();
            
            cod_med = conecta_medico.rs.getInt("cod_medico");
             
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Médico não encontrado!" + ex);
        }
        
        return cod_med;
        
    }
    
    public void buscaPaciente(String nomePaciente){
        
        conecta_paciente.connection();
        conecta_paciente.execulteSQL("select * from pacientes where nome_paciente='" + nomePaciente + "'");
         try {
             
            conecta_paciente.rs.first();
            cod_pac = conecta_paciente.rs.getInt("cod_paciente");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Paciente não encontrado!" + ex);
        }
        
        
    }
    
     public void buscaAgendamento(BeanAgenda agenda){
        
        conecta_medico.connection();
        conecta_medico.execulteSQL("select * from agenda where agenda_data='" + agenda.getData() + "'");
        try {
            conecta_medico.rs.first();
            
            cod_med = conecta_medico.rs.getInt("cod_medico");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Agendamento não encontrado!" + ex);
        }
    }
    
    public void Editar(BeanAgenda bm){
        conecta.connection();
        try {
            PreparedStatement ps = conecta.conn.prepareStatement("update agenda set agenda_status=? where cod_agenda=?");
            ps.setString(1, bm.getStatus());
            ps.setInt(2, bm.getCodigo());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Agendamento em atendimento!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados! \n");
        }
        conecta.disconnection();
    }
    
    public BeanAgenda buscaAgendaPorCodigo(int cod){
        BeanAgenda agend = new BeanAgenda();
        conecta.connection();
        conecta.execulteSQL("select * from agenda inner join pacientes on agenda_codpac=cod_paciente inner join medicos on agenda_codmed=cod_medico where cod_agenda='" + cod + "'");
        try {
            conecta.rs.first();
            agend.setNome_paciente(conecta.rs.getString("nome_paciente"));
            agend.setNome_medico(conecta.rs.getString("nome_medico"));
            agend.setMotivo(conecta.rs.getString("agenda_motivo"));
            agend.setPaciente_nascimento(conecta.rs.getString("nasc_paciente"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Agendamento por código não encontrado!" + ex);
        }
        
        return agend;
        
    }
    
    
    
}
