package importacao.controller.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import importacao.model.ProdutoSetor;

public class ProdutoSetorController extends Controller<ProdutoSetor> {

	public ProdutoSetor setor = null;

	public ProdutoSetorController() {
		this.set(new ProdutoSetor());
	}

	public void inserir() throws SQLException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

		PreparedStatement ps;

		ps = con.prepareStatement(sql);

		int pos = 1;

		for (String nome : fieldslist) {
			String key = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
			Field field = this.get().getClass().getDeclaredField(nome);
			Class cls = setor.getClass();

			if (field.getType().getSimpleName().equals("int")) {
				Method mtd = cls.getMethod("get" + key, null);
				int tmp1 = Integer.parseInt(mtd.invoke(setor, null).toString());
				ps.setInt(pos, tmp1);
			}

			if (field.getType().equals(String.class)) {

				Method mtd = cls.getMethod("get" + key, null);

				Object tmp1 = mtd.invoke(setor, null);
				String tmp2 = tmp1 != null ? tmp1.toString() : null;

				if (tmp2 != null) {
					ps.setString(pos, tmp2);
				} else {
					ps.setNull(pos, Types.VARCHAR);
				}
			}

			if (field.getType().equals(LocalDate.class)) {

				Method mtd = cls.getMethod("get" + key, null);
				ps.setObject(pos, mtd.invoke(setor, null));
			}

			if (field.getType().equals(LocalTime.class)) {
				Method mtd = cls.getMethod("get" + key, null);
				ps.setObject(pos, mtd.invoke(setor, null));
			}

			pos++;
		}

		ps.executeUpdate();// Ainda não foi commitado
		this.con.commit();
	}

}
