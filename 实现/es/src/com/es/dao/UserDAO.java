package com.es.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.es.model.User;
import com.es.util.DB;
import com.es.vo.UserInfo;

public class UserDAO {

	public User match(String name) {
		Connection conn = DB.createConn();
		String sql = "select *  from user where name = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		User u = null;
		try {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setPassword(rs.getString("password"));
				u.setId(rs.getInt("id"));
				u.setEmail(rs.getString("email"));
				u.setTelephone(rs.getString("telephone"));
				u.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return u;
	}

	public void add(UserInfo user) {
		Connection conn = DB.createConn();
		String sql = "insert into user values (null, ?, ? , ? , ?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}

}
