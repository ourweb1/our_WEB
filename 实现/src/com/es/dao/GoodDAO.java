package com.es.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.es.model.Good;
import com.es.model.User;
import com.es.util.DB;
import com.es.vo.GoodInfo;

public class GoodDAO {

	public List<Good> selectAll(GoodInfo good) {
		Connection conn = DB.createConn();
		String sql = "select * from good join good_category on good.id = good_category.good_id"
				+ " where good_category.category_id = " + good.getId() + ";";
		PreparedStatement ps = DB.prepare(conn, sql);
		List<Good> goods = new ArrayList<Good>();
		try {
			ResultSet rs = ps.executeQuery();
			Good c = null;
			while(rs.next()) {
				c = new Good();
				c.setDetail(rs.getString("detail"));
				c.setId(rs.getInt("id"));
				c.setImgSrc(rs.getString("img_src"));
				c.setName(rs.getString("name"));
				c.setPrice(rs.getDouble("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return goods;
	}

	public Good select(GoodInfo good) {
		Connection conn = DB.createConn();
		String sql = "select * from good join user on good.user_id = user.id where good.id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		Good c = null;
		User u = null;
		try {
			ps.setInt(1, good.getId());
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				c = new Good();
				c.setDetail(rs.getString("detail"));
				c.setId(rs.getInt("id"));
				c.setImgSrc(rs.getString("img_src"));
				c.setName(rs.getString("name"));
				c.setPrice(rs.getDouble("price"));
				u = new User();
				u.setEmail(rs.getString("email"));
				u.setId(rs.getInt("user_id"));
				u.setName(rs.getString("user.name"));
				u.setTelephone(rs.getString("telephone"));
				c.setUser(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return c;
	}

	public void insert(GoodInfo good) {
		Connection conn = DB.createConn();
		String sql = "insert into goods values (null, ?, ? , ? , ?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setLong(1, good.getUserInfo().getId());
			ps.setString(2, good.getName());
			ps.setString(3, good.getDetail());
			ps.setLong(4, (long) good.getPrice());
			ps.setString(5, good.getImgSrc());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}

}
