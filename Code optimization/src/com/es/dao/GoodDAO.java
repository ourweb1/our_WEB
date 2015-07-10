package com.es.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.es.action.GoodAction;
import com.es.model.Good;
import com.es.model.User;
import com.es.util.DB;
import com.es.vo.CategoryInfo;
import com.es.vo.GoodInfo;

public class GoodDAO {

	public List<Good> selectAll(GoodInfo good , int page) {
		Connection conn = DB.createConn();
		String sql = "select * from good join good_category on good.id = good_category.good_id"
				+ " where good_category.category_id = " + good.getId() + " or floor(good_category.category_id / 10) ="
				+good.getId()+ " order by good.create_time desc" +  " limit "+ (page-1)*GoodAction.PAGE_SIZE  +","+ GoodAction.PAGE_SIZE +";";
System.out.println(sql);
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
				c.setCreateTime(rs.getTimestamp("create_time"));
				goods.add(c);
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
		String sql = "select good.id as g_id , good.name as g_name , detail , price , good.img_src as g_img_src"
				+ " , good.create_time as g_create_time , user.id ,user.name,user.email ,user.telephone "
				+ "from good join user on good.user_id = user.id where good.id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		Good c = null;
		User u = null;
		try {
			ps.setInt(1, good.getId());
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				c = new Good();
				c.setDetail(rs.getString("detail"));
				c.setId(rs.getInt("g_id"));
				c.setImgSrc(rs.getString("g_img_src"));
				c.setName(rs.getString("g_name"));
				c.setPrice(rs.getDouble("price"));
				c.setCreateTime(rs.getTimestamp("g_create_time"));
				u = new User();
				u.setEmail(rs.getString("email"));
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
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

	public int insert(GoodInfo good) {
		Connection conn = DB.createConn();
		String sql = "insert into good values (null, ?, ? , ? , ?,?,?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		int id = 0;
		try {
			ps.setLong(1, good.getUser().getId());
			ps.setString(2, good.getName());
			ps.setString(3, good.getDetail());
			ps.setLong(4, (long) good.getPrice());
			ps.setString(5, good.getImgSrc());
			ps.setTimestamp(6, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			id = DB.getInsertId(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return id;
	}
	
	public void insertGoodCategory(int goodId , int categoryId) {
		Connection conn = DB.createConn();
		String sql = "insert into good_category values(" + goodId + "," + categoryId + ")";
		Statement stmt = DB.getStatement(conn);
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(stmt);
			DB.close(conn);
		}
	}

	public int count(List<CategoryInfo> categories) {
		if(categories == null || categories.isEmpty())
			return 0;
		String sql = "select count(*) from good_category where category_id="+categories.get(0).getId()+
				" or floor(good_category.category_id / 10)="+ categories.get(0).getId() +";";
System.out.println(sql);
		Connection conn = DB.createConn();
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = null;
		int count = 0;
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(conn);
		}
		return count;
	}
	
	

}
