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
    private String DB_DRIVER ="org.postgresql.Driver";
    private String DB_HOST ="";
    private String DB_PORT = "5432";
    private String DB_DATABASE  = "";
    private String DB_USER  = "";
    private String DB_PASSWORD  = "";

    public boolean getConnectionFactory() throws SQLException {
        try {

            Class.forName(this.DB_DRIVER);
            this.con = DriverManager.getConnection("jdbc:postgresql://" + this.getDB_HOST() + ":" + this.getDB_PORT() + "/" + this.getDB_DATABASE(), this.getDB_USER(), this.getDB_PASSWORD());
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;

        }
    }
    public String getDB_DRIVER() {
		return DB_DRIVER;
	}

	public void setDB_DRIVER(String dB_DRIVER) {
		DB_DRIVER = dB_DRIVER;
	}

	public String getDB_HOST() {
		return DB_HOST;
	}

	public void setDB_HOST(String dB_HOST) {
		DB_HOST = dB_HOST;
	}

	public String getDB_PORT() {
		return DB_PORT;
	}

	public void setDB_PORT(String dB_PORT) {
		DB_PORT = dB_PORT;
	}

	public String getDB_DATABASE() {
		return DB_DATABASE;
	}

	public void setDB_DATABASE(String dB_DATABASE) {
		DB_DATABASE = dB_DATABASE;
	}

	public String getDB_USER() {
		return DB_USER;
	}

	public void setDB_USER(String dB_USER) {
		DB_USER = dB_USER;
	}

	public String getDB_PASSWORD() {
		return DB_PASSWORD;
	}

	public void setDB_PASSWORD(String dB_PASSWORD) {
		DB_PASSWORD = dB_PASSWORD;
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

   
}
