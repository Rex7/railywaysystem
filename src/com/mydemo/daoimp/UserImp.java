package com.mydemo.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mydemo.dao.UserDao;
import com.mydemo.model.User;
import com.mydemo.operations.Dbhelper;

public class UserImp implements UserDao {

	@Override
	public String login(User user) {
		String message="failed";
		Connection con=Dbhelper.getConnection();
		String sql = "select * from userrecords where username = ? and userpass = ? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getUserPass());
		
			
			int update = pst.executeUpdate();
			if (update > 0) {
				message="success";
			}
			
		} catch (SQLException e) {

			
		}
		return message;

		
	}

}
