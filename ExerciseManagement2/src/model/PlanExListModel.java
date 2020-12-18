package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class PlanExListModel extends AbstractListModel<String> {
	ArrayList<String> pExList;
	
	public PlanExListModel(ArrayList<String> data) {
		// TODO Auto-generated constructor stub
		pExList = data;
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return pExList.size();
	}
	
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return pExList.get(index);
	}



}
