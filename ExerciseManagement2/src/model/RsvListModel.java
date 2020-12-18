package model;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.AbstractListModel;

import data.ExManaDAO;
import data.MemberTO;
import data.TScheduleTO;

public class RsvListModel extends AbstractListModel<String> {
	ArrayList<ArrayList> datas;
	
	public RsvListModel(String userId, Calendar sCal, Calendar eCal) {
		// TODO Auto-generated constructor stub
		ExManaDAO dao = new ExManaDAO();
		datas = dao.searchTSchedule_tra(userId, sCal, eCal);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return datas.size();
	}
	
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		String result = "";

		TScheduleTO tsto = (TScheduleTO) datas.get(index).get(0);
		MemberTO mto = (MemberTO) datas.get(index).get(1);
		
		result = String.format("%s %s %s %s %s", tsto.getDate(), mto.getMname(), mto.getGender(), mto.getMcontact(), mto.getMserial());
		return result;
	}


}
