package importacao.controller.uniplus;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import importacao.controller.core.ProdutoSetorController;
import importacao.dao.ConnectionFactoryPostgre;
import importacao.model.ProdutoSetor;

public class ProdutoSetorControllerUniplus extends ProdutoSetorController {

	private void setProduto(ResultSet rs) {
		setor = new ProdutoSetor();
		try {

			setor.setIdsetor(rs.getInt("codigo"));
			setor.setNome(rs.getString("nome"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inserirTodos(ConnectionFactoryPostgre novo, ConnectionFactoryPostgre antigo) {
		PreparedStatement ps_novo = null;

		PreparedStatement ps_antigo = null;

		ResultSet resultSet = null;
		try {
			ps_antigo = antigo.getCon().prepareStatement("SELECT codigo,nome FROM hierarquia");
			resultSet = ps_antigo.executeQuery();

			ProdutoSetorControllerUniplus setor_;
			setor_ = new ProdutoSetorControllerUniplus();
			setor_.setCon(novo.getCon());

			while (resultSet.next()) {

				setor_.setProduto(resultSet);
				setor_.inserir();
			}

			System.out.println("produto_setor inserido");
		} catch (SQLException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex) {

			Logger.getLogger(ProdutoSetorControllerUniplus.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {

			if (ps_novo != null) {
				ps_novo.close();
			}
			if (ps_antigo != null) {
				ps_antigo.close();
			}
		} catch (SQLException ex) {
			// Logger.getLogger(ProdutoControllerDaxii.class.getName()).log(Level.SEVERE,
			// null, ex);
		}
	}

	public static void main(String[] args) throws SQLException {

		ProdutoSetorControllerUniplus setor = new ProdutoSetorControllerUniplus();
		ConnectionFactoryPostgre novo = new ConnectionFactoryPostgre();
		ConnectionFactoryPostgre antigo = new ConnectionFactoryPostgre();

		novo.setDB_USER(ConnectionFactoryPostgre.getConfiginfo("user"));
		novo.setDB_PASSWORD(ConnectionFactoryPostgre.getConfiginfo("pass"));
		novo.setDB_HOST(ConnectionFactoryPostgre.getConfiginfo("ip"));
		novo.setDB_DATABASE(ConnectionFactoryPostgre.getConfiginfo("db"));

		novo.getConnectionFactory();

		antigo.setDB_USER(ConnectionFactoryPostgre.getConfiginfo("user_"));
		antigo.setDB_PASSWORD(ConnectionFactoryPostgre.getConfiginfo("pass_"));
		antigo.setDB_HOST(ConnectionFactoryPostgre.getConfiginfo("ip_"));
		antigo.setDB_DATABASE(ConnectionFactoryPostgre.getConfiginfo("db_"));

		antigo.getConnectionFactory();

		setor.inserirTodos(novo, antigo);

	}
}
