package com.mydemo.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mydemo.dao.RoutesDao;
import com.mydemo.model.Routes;
import com.mydemo.operations.Dbhelper;

public class RoutesImp implements RoutesDao {

	@Override
	public Routes getAllTrainDetails(Routes routes) {
		try {
			Connection con=Dbhelper.getConnection();
			String sql="select distinct(r1.trainno) from routes r1 join  routes r2\r\n" + 
					"on (r1.routeid=r2.routeid)\r\n" + 
					"where r1.routename = ? and r2.routename= ? ";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, routes.getSrc());
			pst.setString(2, routes.getDesc());
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				routes.getTrainList().add(rs.getInt(1));
			}
			
			
		}
		catch(Exception ex) {
			System.out.println("ex"+ex.getMessage());
		}
		return routes;
		
	}

}
