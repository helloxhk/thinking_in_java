package com.xhk.demo.annotation.SQLAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xhk
 * @time 2018-12-24 14:01
 */
public class TableCreater {

	public static void execute(String... classNames) throws ClassNotFoundException {
		for (String className : classNames) {
			Class<?> clazz = Class.forName(className);

			DBTable dbTable = clazz.getDeclaredAnnotation(DBTable.class);
			if (dbTable == null) {
				System.out.println("No DBTable Annotation in class " + className);
				continue;
			}
			String tableName = dbTable.name();
			if (tableName.length() < 1) {
				tableName = clazz.getName().toUpperCase();
			}

			List<String> columns = new ArrayList<>();
			for (Field field : clazz.getDeclaredFields()) {
				String columnName;
				SQLString sqlString = field.getDeclaredAnnotation(SQLString.class);
				if (sqlString != null) {
					columnName = sqlString.name();
					if (columnName.length() < 1) {
						columnName = field.getName().toUpperCase();
					}
					columns.add(columnName + " VARCHAR(" + sqlString.value() + ") " + getConstraints(sqlString.constraints()));
				}

				SQLInteger sqlInteger = field.getDeclaredAnnotation(SQLInteger.class);
				if (sqlInteger != null) {
					columnName = sqlInteger.name();
					if (columnName.length() < 1) {
						columnName = field.getName().toUpperCase();
					}
					columns.add(columnName + " INTEGER " + getConstraints(sqlInteger.constraints()));
				}
			}

			StringBuilder tableCreate = new StringBuilder("CREATE TABLE " + tableName + "(");
			for (String column : columns) {
				tableCreate.append("\n\t" + column + ",");
			}
			String result = tableCreate.substring(0, tableCreate.length() - 1) + "\n);";
			System.out.println(result);
		}
	}

	private static String getConstraints(Constraints constraints) {
		String result = "";
		if (!constraints.allowNull()) {
			result += "NOT NULL ";
		}
		if (constraints.primaryKey()) {
			result += "PRIMARY KEY ";
		}
		if (constraints.unique()) {
			result += "UNIQUE ";
		}
		return result;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		execute("com.xhk.demo.annotation.Member");
	}
}
