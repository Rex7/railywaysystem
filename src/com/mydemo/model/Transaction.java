package com.mydemo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
private String customerName;
private int trainNo;
private float totalCost;
private int noOfPassenger;

public int getNoOfPassenger() {
	return noOfPassenger;
}
public void setNoOfPassenger(int noOfPassenger) {
	this.noOfPassenger = noOfPassenger;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public int getTrainNo() {
	return trainNo;
}
public void setTrainNo(int trainNo) {
	this.trainNo = trainNo;
}
public float getTotalCost() {
	return totalCost;
}
public void setTotalCost(float totalCost) {
	this.totalCost = totalCost;
} 


}
