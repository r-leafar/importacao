package importacao.controller.uniplus;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import importacao.controller.core.ProdutoController;
import importacao.dao.ConnectionFactoryPostgre;
import importacao.model.Produto;

public class ProdutoControllerUniplus extends ProdutoController {


	private void setProduto(ResultSet rs) {
		produto = new Produto();
		try {

			produto.setIdproduto(rs.getInt("codigo"));
			produto.setDescricaoproduto(rs.getString("nome"));
			produto.setNcm(rs.getString("ncm"));
			produto.setValorvenda1(rs.getDouble("preco"));
			produto.setValorcustocaixa(produto.getValorvenda1() * 0.5);
			produto.setMargemvenda1(50);
			produto.setCodigobarra1(rs.getString("ean"));

			double icms = rs.getDouble("aliquotaicmsinterna");

			if (icms == 0) {
				produto.setIcms("FF");
				produto.setCfopsat("5405");
			} else {
				produto.setIcms(icms);
				produto.setCfopsat("5102");
			}

			produto.setCodigocsosn(rs.getString("situacaotributariasn"));

			switch (rs.getInt("idunidademedida")) {
			case 4:
				produto.setIdunidade(11);
				break;
			case 12:
				produto.setIdunidade(15);
				break;
			case 13:
				produto.setIdunidade(13);
				break;
			case 14:
				produto.setIdunidade(21);
				break;
			case 17:
				produto.setIdunidade(18);
				produto.setPesanabalanca(true);
				produto.setPesaporquilo(true);
				break;
			case 25:
				produto.setIdunidade(10);
				break;
			case 30:
				produto.setIdunidade(7);
				break;

			default:
				produto.setIdunidade(7);
			}

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
			ps_antigo = antigo.getCon().prepareStatement(
					"SELECT codigo,nome,ncm,preco,ean,aliquotaicmsinterna,idunidademedida,situacaotributariasn FROM produto");
			resultSet = ps_antigo.executeQuery();

			ProdutoControllerUniplus prod;
			prod = new ProdutoControllerUniplus();
			prod.set(new Produto());
			prod.setCon(novo.getCon());

			while (resultSet.next()) {

				prod.setProduto(resultSet);
				prod.inserir();
			}

			System.out.println("Produtos inseridos...");
		} catch (SQLException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException | NoSuchFieldException ex) {

			Logger.getLogger(ProdutoControllerUniplus.class.getName()).log(Level.SEVERE, null, ex);
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

	public static void main(String[] args) throws SQLException, NoSuchFieldException {

		ProdutoControllerUniplus produto = new ProdutoControllerUniplus();
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

		produto.inserirTodos(novo, antigo);

	}

}
