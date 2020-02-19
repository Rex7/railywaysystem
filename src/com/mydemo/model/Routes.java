package com.mydemo.model;

import java.util.ArrayList;
import java.util.List;

public class Routes {
	private int routeId,trainNo;
	private String routeName;
	private String src,desc;
	private List<Integer> trainList=new ArrayList<>();
	
	public List<Integer> getTrainList() {
		return trainList;
	}
	public void setTrainList(List<Integer> trainList) {
		this.trainList = trainList;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public int getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
	}
	


}
