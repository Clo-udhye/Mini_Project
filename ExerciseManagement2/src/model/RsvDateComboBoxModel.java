package model;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import data.ExManaDAO;
import data.TScheduleTO;

public class RsvDateComboBoxModel extends DefaultComboBoxModel<String> {
	ArrayList<TScheduleTO> datas;
	
	public RsvDateComboBoxModel(String mserial) {
		// TODO Auto-generated constructor stub
		ExManaDAO dao = new ExManaDAO();
		datas = dao.searchMyRsvList(mserial);
	}
	
	public int getSize() {
		// TODO Auto-generated method stub
		return datas.size();
	}
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		String result = datas.get(index).getDate().substring(0, datas.get(index).getDate().lastIndexOf(":"));
				
		return result;
	}
}
