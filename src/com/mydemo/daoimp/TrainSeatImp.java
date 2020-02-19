package com.mydemo.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.mydemo.dao.TrainSeatDao;
import com.mydemo.model.TrainSeat;
import com.mydemo.operations.Dbhelper;

public class TrainSeatImp implements TrainSeatDao {

	@Override
	public boolean checkSeats(TrainSeat train) {
		boolean isSeatsAvaiable = false;
		Connection con = Dbhelper.getConnection();

		String sql = " SELECT seats.sleeper_class " + 
				"FROM trainseats " + 
				"INNER JOIN seats ON trainseats.trainno = seats.trainno " + 
				"and seats.trainno = ?" + 
				" ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, train.getTrainNo());
			ResultSet result = pst.executeQuery();
			ResultSetMetaData ms = result.getMetaData();
			System.out.println(ms.getColumnName(1));

			while (result.next()) {
				int totalSeats = result.getInt(1);
				if (totalSeats >= train.getNoOfSeats()) {
					isSeatsAvaiable = true;
				}
                                System.out.println("train "+totalSeats);

			}
                         System.out.println("train ");
		} catch (SQLException ex) {
			System.out.println("TrainSeats " + ex);
		}
		return isSeatsAvaiable;
	}

	@Override
	public void updateCheats(int trainNo, int noOfSeats) {

		Connection con = Dbhelper.getConnection();
		String sql = "update seats set sleeper_class = sleeper_class - ?  where trainno = ? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, noOfSeats);
			pst.setInt(2, trainNo);
			int updated = pst.executeUpdate();
			if (updated > 0) {
				System.out.println("ticket reduced");
			}
			else {
				System.out.println("something went wrong");
			}

		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

}
