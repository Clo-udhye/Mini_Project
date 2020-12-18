package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import data.ExManaDAO;
import data.ExerciseTO;

public class ExListModel extends AbstractListModel<String> {
	ArrayList<ExerciseTO> exList;
	
	public ExListModel(String expart) {
		// TODO Auto-generated constructor stub
		ExManaDAO dao = new ExManaDAO();
		exList = dao.searchExList(expart);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return exList.size();
	}
	
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		ExerciseTO to =  exList.get(index);
		
		return to.getExname();
	}
}
