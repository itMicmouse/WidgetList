package org.micmource.lib_greendao3.dbhelper;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.Iterator;
import java.util.Map;

public class DbUpdateHelper {
	/**
	 * 添加表
	 * @param db
	 * @param sql
	 */
	public void createTable(SQLiteDatabase db,String sql){
		db.execSQL(sql);
	}
	/**
	 * 删除表
	 * @param db
	 * @param tableName
	 */
	public void dropTable(SQLiteDatabase db,String tableName){
		String sql = "DROP TABLE IF EXISTS "+ tableName;
		db.execSQL(sql);
	}

	/**
	 *
	 * @param db
	 * @param tableName
	 * @param columnvaluse
	 * @param position
     */
	public void addcolumn(SQLiteDatabase db,String tableName,String columnvaluse,int position){
		try {
			String sql = "select * from "+tableName;
			String sqlstructure ="SELECT sql FROM sqlite_master WHERE type='table' AND name = '"+tableName+"'";
			Cursor cursor2 = db.rawQuery(sqlstructure, null);
			String structuresql="";
			if(cursor2.moveToFirst())
				structuresql = cursor2.getString(0);
			Cursor cursor = db.rawQuery(sql, null);
			String[] columnNames = cursor.getColumnNames();
			cursor.close();
			cursor2.close();
			String[] split = structuresql.split(",");
			//1 将表A重命名，改为A_temp临时表
			String tempTableName = tableName + "_temp";
			String sqlalert = "alter table "+ tableName + " RENAME TO "+ tempTableName;
			db.execSQL(sqlalert);
			//2 创建新表A
			StringBuffer sqlsb = new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				if(i==(position-1)){
					sqlsb.append(columnvaluse);
					sqlsb.append(",");
				}
				sqlsb.append(split[i]);
				if(i!=split.length-1)
				sqlsb.append(",");
			}
			db.execSQL(sqlsb.toString());
			StringBuffer columstring = new StringBuffer();
			for (String string : columnNames) {
				columstring.append(string);
				columstring.append(",");
			}
			String string = columstring.subSequence(0, columstring.length()-1).toString();
			//3 将临时表的数据添加到表A
			sql =   "INSERT INTO " + tableName +  
			        " (" + string + ") " +  
			        " SELECT " + string + " FROM " + tempTableName;  
			db.execSQL(sql);
			//4 将临时表删除
			db.execSQL("drop table " + tempTableName);
		} catch (SQLException e) {
			// TODO: 2016/12/15 上报错误
		}
	}
	/**
	 * 
	 * @param db
	 * @param tableName
	 * @param columnvaluse eg "orderNum"  int,
	 */
	public void deletecolumn(SQLiteDatabase db,String tableName,String columnvaluse){
		int position=0;
		String sql = "select * from "+tableName;
		String sqlstructure ="SELECT sql FROM sqlite_master WHERE type='table' AND name = '"+tableName+"'";
		Cursor cursor2 = db.rawQuery(sqlstructure, null);
		String structuresql="";
		if(cursor2.moveToFirst())
			structuresql = cursor2.getString(0);
		Cursor cursor = db.rawQuery(sql, null);
		String[] columnNames = cursor.getColumnNames();
		
		cursor.close();
		cursor2.close();
		
		StringBuffer columstring = new StringBuffer();
		for (int i = 0; i < columnNames.length; i++) {
			if(columnNames[i].equals(columnvaluse)){
				position=i;
				continue;
			}
			columstring.append(columnNames[i]);
			columstring.append(",");
		}
		String string = columstring.subSequence(0, columstring.length()-1).toString();
		
		String[] split = structuresql.split(",");
		//1 将表A重命名，改为A_temp临时表
		String tempTableName = tableName + "_temp";
		String sqlalert = "alter table "+ tableName + " RENAME TO "+ tempTableName;
		db.execSQL(sqlalert);
		//2 创建新表A
		StringBuffer sqlsb = new StringBuffer();
		for (int i = 0; i < split.length; i++) {
			if(i==position){
				continue;
			}
			sqlsb.append(split[i]);
			if(i!=split.length-1)
				sqlsb.append(",");
		}
		db.execSQL(sqlsb.toString());
		//3 将临时表的数据添加到表A
		sql =   "INSERT INTO " + tableName +  
				" (" + string + ") " +  
				" SELECT " + string + " FROM " + tempTableName;  
		db.execSQL(sql);
		//4 将临时表删除
		db.execSQL("drop table " + tempTableName);
	}

	/**
	 * 修改字段的名字 只能用于一个字段的修改
	 * @param db
	 * @param tableName
	 * @param columnvaluse
	 * @param newcolumName
     */
	public void updatecolumn(SQLiteDatabase db,String tableName,String columnvaluse,String newcolumName){
		try {
			int position=0;
			String sql = "select * from "+tableName;
			String sqlstructure ="SELECT sql FROM sqlite_master WHERE type='table' AND name = '"+tableName+"'";
			Cursor cursor2 = db.rawQuery(sqlstructure, null);
			String structuresql="";
			if(cursor2.moveToFirst())
				structuresql = cursor2.getString(0);
			Cursor cursor = db.rawQuery(sql, null);
			String[] columnNames = cursor.getColumnNames();
			
			cursor.close();
			cursor2.close();
			
			StringBuffer columstring = new StringBuffer();
			for (int i = 0; i < columnNames.length; i++) {
				columstring.append(columnNames[i]);
				columstring.append(",");
			}
			String string = columstring.subSequence(0, columstring.length()-1).toString();
			
			
			//1 将表A重命名，改为A_temp临时表
			String tempTableName = tableName + "_temp";
			String sqlalert = "alter table "+ tableName + " RENAME TO "+ tempTableName;
			db.execSQL(sqlalert);
			//2 创建新表A
			structuresql = structuresql.replace(columnvaluse, newcolumName);
			db.execSQL(structuresql.toString());
			
			//3 将临时表的数据添加到表A
			sql =   "INSERT INTO " + tableName +  
					" (" + string.replace(columnvaluse, newcolumName) + ") " +  
					" SELECT " + string+ " FROM " + tempTableName;  
			db.execSQL(sql);
			//4 将临时表删除
			db.execSQL("drop table " + tempTableName);
		} catch (SQLException e) {
			// TODO: 2016/12/15 上报错误
			e.printStackTrace();
		}
	}

	/**
	 * 修改字段的名字
	 * @param db
	 * @param tableName
	 * @param replacemap
     */
	@SuppressLint("NewApi") public void updatecolumn(SQLiteDatabase db,String tableName,Map<String, String> replacemap){
		try {
			int position=0;
			String sql = "select * from "+tableName;
			String sqlstructure ="SELECT sql FROM sqlite_master WHERE type='table' AND name = '"+tableName+"'";
			Cursor cursor2 = db.rawQuery(sqlstructure, null);
			String structuresql="";
			if(cursor2.moveToFirst())
				structuresql = cursor2.getString(0);
			Cursor cursor = db.rawQuery(sql, null);
			String[] columnNames = cursor.getColumnNames();
			
			cursor.close();
			cursor2.close();
			
			StringBuffer columstring = new StringBuffer();
			for (int i = 0; i < columnNames.length; i++) {
				columstring.append(columnNames[i]);
				columstring.append(",");
			}
			String string = columstring.subSequence(0, columstring.length()-1).toString();
			String stringold = columstring.subSequence(0, columstring.length()-1).toString();
			
			Iterator iter = replacemap.entrySet().iterator();
			
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = entry.getKey().toString();
				String val = entry.getValue().toString();
				structuresql = structuresql.replace(key, val);
				string = string.replace(key, val);
			}
			
			//1 将表A重命名，改为A_temp临时表
			String tempTableName = tableName + "_temp";
			String sqlalert = "alter table "+ tableName + " RENAME TO "+ tempTableName;
			db.execSQL(sqlalert);
			//2 创建新表A
			db.execSQL(structuresql.toString());
			
			//3 将临时表的数据添加到表A
			sql =   "INSERT INTO " + tableName +  
					" (" + string + ") " +  
					" SELECT " + stringold+ " FROM " + tempTableName;  
			db.execSQL(sql);
			//4 将临时表删除
			db.execSQL("drop table " + tempTableName);
		} catch (SQLException e) {
			// TODO: 2016/12/15 上报错误
			e.printStackTrace();
		}
	}
}
