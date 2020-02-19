package com.mydemo.dao;

import com.mydemo.model.TrainSeat;
import com.mydemo.model.TrainSeat;

public interface TrainSeatDao {
	public boolean checkSeats(TrainSeat seats);
	public void updateCheats(int trainNo,int noOfSeats);

}
