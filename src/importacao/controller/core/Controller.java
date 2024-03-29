package importacao.controller.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import importacao.annotations.Table;

public class Controller<T> {

	List<String> fieldslist;
	public String sql = null;
	private T model;
	String tablename;
	public Connection con;

	public void init() {

		try {
			if (sql == null) {
				// Pega o nome da tabela pela annotation
				Annotation annotation = this.get().getClass().getAnnotation(Table.class);
				Table table = (Table) annotation;
				this.tablename = table.name();
				// Instancia o arraylist
				fieldslist = new ArrayList<String>();
				// pega o nome de todos os atributos da classe produto
				// e adiciona no arraylist
				Field[] fieldsraw = this.get().getClass().getDeclaredFields();
				for (Field f : fieldsraw) {
					fieldslist.add(f.getName());
				}

				// cria a query de insert
				String sqlfield = "INSERT INTO " + this.tablename + " (";
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
		} catch (NullPointerException ex) {
			System.out.println("Classe model n�o instanciada.");
		}

	}

	public void set(T t) {
		this.model = t;
		init();
	}

	public T get() {
		return this.model;
	}

	public void setCon(Connection con) throws SQLException {
		this.con = con;
		this.con.setAutoCommit(false);
	}

}
