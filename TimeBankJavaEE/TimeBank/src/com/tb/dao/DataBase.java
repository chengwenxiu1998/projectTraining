package com.tb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
*@author 锟斤拷锟斤拷锟斤拷
*@version 锟斤拷锟斤拷时锟斤拷:2018锟斤拷12锟斤拷6锟斤拷 锟斤拷锟斤拷10:07:05
*@ClassName 锟斤拷锟斤拷锟斤拷
*@Description 锟斤拷锟斤拷锟斤拷
*/

public class DataBase {
	/*
	 * 锟斤拷取锟斤拷锟斤拷锟捷匡拷锟斤拷锟斤拷锟�
	 */
	public static Connection getConnection() {
		Connection conn=null;
		try {
			//1锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
			Class.forName("com.mysql.jdbc.Driver");
			//2锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
//			conn=DriverManager.getConnection("jdbc:mysql://106.12.151.7:3306/timebank?useUnicode=true&characterEncoding=UTF-8","root","timebank");
			conn=DriverManager.getConnection("jdbc:mysql://106.14.222.186:3306/timebank?useUnicode=true&characterEncoding=UTF-8","root","123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	//锟截憋拷锟斤拷锟捷匡拷
	public static void close(Connection conn,Statement stmt,ResultSet res) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(res!=null) {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
