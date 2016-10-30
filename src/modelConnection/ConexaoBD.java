/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexaoBD {
    
    //responsável por fazer as pesquisas
    public Statement stm;
    //responsável por guardar as pesquisas
    public ResultSet rs;
    public String driver = "org.postgresql.Driver";
    private String path = "jdbc:postgresql://localhost:5432/pep";
    private String user = "postgres";
    private String password = "123mudar";
    public Connection conn;
    
    // método responsável por realizar a conexão com a base de dados
    public void connection(){
        try {
            System.setProperty("jdbc.Drivers", driver);
            conn = DriverManager.getConnection(path, user, password);
            //JOptionPane.showMessageDialog(null, "Conexão efetuada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o Banco de Dados: \n" + ex.getMessage());
        }
        
    }
    
    public void execulteSQL(String sql ){
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o Banco de Dados: \n" + ex.getMessage());
        }
    }
    
    // método responsável por realizar a desconexão com a base de dados
    public void disconnection(){
        try {
            conn.close();
            //JOptionPane.showMessageDialog(null, "Desconexão efetuada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar fechar a conexão com o Banco de Dados: \n" + ex.getMessage());
        }
    }
    
}
