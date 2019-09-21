package importacao.controller.uniplus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import importacao.model.Produto;

public class ProdutoController {

	Connection con;
	Produto produto = null;
	List<String> fieldslist;
	String sql="";

	public ProdutoController() {
		super();
		init();
	}

	public void init() {
		//Instancia o arraylist
		fieldslist = new ArrayList<String>();
		//pega o nome de todos os atributos da classe produto
		//e adiciona no arraylist
		Field[] fieldsraw = Produto.class.getDeclaredFields();
		for (Field f : fieldsraw) {
			fieldslist.add(f.getName());
		}
		

		//cria a query de insert
		String sqlfield = "INSERT INTO produto (";
		String sqlparam = "VALUES (";

		for (String campo : fieldslist) {
			sqlfield += campo.concat(",");
			sqlparam += "?,";
		}

		sqlfield = sqlfield.substring(0, sqlfield.length() - 1);
		sqlfield = sqlfield.concat(") ");

		sqlparam = sqlparam.substring(0, sqlparam.length() - 1);
		sqlparam = sqlparam.concat(") ");

		sql = sqlfield.concat(sqlparam);
		
		
	}

	public void inserir() throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

		PreparedStatement ps;

		ps = con.prepareStatement(sql);

		int pos = 1;

		for (String nome : fieldslist) {
			String key = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
			Field field = Produto.class.getDeclaredField(nome);
			Class cls = produto.getClass();

			if (field.getType().equals(String.class)) {

				Method mtd = cls.getMethod("get" + key, null);

				Object tmp1 = mtd.invoke(produto, null);
				String tmp2 = tmp1 != null ? tmp1.toString() : null;

				if (tmp2 != null) {
					ps.setString(pos, tmp2);
				} else {
					ps.setNull(pos, Types.VARCHAR);
				}

//				System.out.print(key + ":");				
//				System.out.println(mtd.invoke(produto, null));

			}
			if (field.getType().getSimpleName().equals("int")) {

				Method mtd = cls.getMethod("get" + key, null);
				int tmp1 = Integer.parseInt(mtd.invoke(produto, null).toString());

				if (Arrays.asList("idfornecedor", "idmarca", "idsetor", "idgrupo", "idsubgrupo", "idlinha")
						.contains(nome) && tmp1 == 0) {
					ps.setNull(pos, Types.INTEGER);
				} else {
					ps.setInt(pos, tmp1);
				}

//				System.out.print(key + ":");
//				System.out.println(mtd.invoke(produto, null));
			}
			if (field.getType().getSimpleName().equals("boolean")) {
				Method mtd = cls.getMethod("is" + key, null);

				ps.setBoolean(pos, Boolean.parseBoolean(mtd.invoke(produto, null).toString()));

//				System.out.print(key + ":");
//				System.out.println(mtd.invoke(produto, null));
			}

			if (field.getType().getSimpleName().equals("double")) {
				Method mtd = cls.getMethod("get" + key, null);

				ps.setDouble(pos, Double.parseDouble(mtd.invoke(produto, null).toString()));

//				System.out.print(key + ":");
//				System.out.println(mtd.invoke(produto, null));
			}

			if (field.getType().equals(LocalDate.class)) {
				Method mtd = cls.getMethod("get" + key, null);

				ps.setObject(pos, mtd.invoke(produto, null));

//				System.out.print(key + ":");
//				System.out.println(mtd.invoke(produto, null));
			}
			pos++;

		}

		ps.executeUpdate();// Ainda não foi commitado

		this.con.commit();

	}

}
