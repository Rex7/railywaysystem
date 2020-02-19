package com.mydemo.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mydemo.dao.TrainCal;
import com.mydemo.model.TrainfareCalculator;
import com.mydemo.operations.Dbhelper;

public class TrainImp implements TrainCal {

	@Override
	public float calculateCost(TrainfareCalculator cal) {
		Connection con=Dbhelper.getConnection();
		float cost = 0;
		String sql="select  sum(r2.ticketcost-r1.ticketcost) * ? as total from routes r1 join  routes r2\r\n" + 
				"on r1.trainno=?" + 
				"where r1.routename = ? and r2.routename = ? ";
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1, cal.getNoOfPassenger());
			pst.setInt(2, cal.getTrainNo());
			 pst.setString(3, cal.getSource());
			 pst.setString(4, cal.getDestination());
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				cost=rst.getFloat(1);
			}
			
		}
		catch(Exception ex) {
			System.out.println("exception "+ ex.getMessage());
		}
		return cost;
	}

}
