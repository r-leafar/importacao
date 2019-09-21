/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importacao.dao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ConnectionFactoryPostgre extends ConnectionFactory {

    private Connection con = null;
    private String ip ="";
    private String porta = "5432";
    private String banco = "";
    private String user = "";
    private String passwd = "";


    public boolean getConnectionFactory() throws SQLException {
        try {

            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection("jdbc:postgresql://" + this.getIp() + ":" + this.getPorta() + "/" + this.getBanco(), this.getUser(), this.getPasswd());
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;

        }
    }

    public boolean verificaTabelas() throws SQLException {

        PreparedStatement ps_post = this.getCon().prepareStatement("SELECT count(*)as total FROM produto limit 10");
        ResultSet rs = ps_post.executeQuery();
        String mensagem = "";
        boolean valido = true;

        if (rs.next()) {
            if (rs.getInt("total") > 0) {
                mensagem += "| Produto |";
                valido = false;
            }

        }
        ps_post = this.getCon().prepareStatement("SELECT count(*)as total FROM cliente limit 10");
        rs = ps_post.executeQuery();

        if (rs.next()) {
            if (rs.getInt("total") > 0) {
                mensagem += "| cliente |";
                valido = false;
            }

        }

        ps_post = this.getCon().prepareStatement("SELECT count(*)as total FROM fornecedor limit 10");
        rs = ps_post.executeQuery();

        if (rs.next()) {
            if (rs.getInt("total") > 0) {
                mensagem += "| fornecedor |";
                valido = false;
            }

        }
         ps_post = this.getCon().prepareStatement("SELECT count(*)as total FROM produto_marca limit 10");
        rs = ps_post.executeQuery();

        if (rs.next()) {
            if (rs.getInt("total") > 0) {
                mensagem += "| produto_marca |";
                valido = false;
            }

        }
        if (!valido) {
            JOptionPane.showMessageDialog(null, "Verifique a(s) tabela(s): \n " + mensagem + " \n ela(s) deve(m) estar vazia(s).");
        }
        return valido;

    }

    public void close() throws SQLException {
        if (this.getCon() != null) {
            this.getCon().close();
        }
    }

    public Connection getCon() {
        return con;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
