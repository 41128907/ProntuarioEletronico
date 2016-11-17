/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelBean;

import java.util.Date;

/**
 *
 * @author carlosantonio
 */
public class BeanAgenda {
    private int codigo ;
    private String nome_medico ;
    private String nome_paciente;
    private String turno;
    private Date data;
    private String status;
    private String motivo;
    private String paciente_nascimento;

    public String getPaciente_nascimento() {
        return paciente_nascimento;
    }

    public void setPaciente_nascimento(String paciente_nascimento) {
        this.paciente_nascimento = paciente_nascimento;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome_medico() {
        return nome_medico;
    }

    public void setNome_medico(String nome_medico) {
        this.nome_medico = nome_medico;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
