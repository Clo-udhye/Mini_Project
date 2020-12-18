package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import data.ExManaDAO;
import data.MemberTO;

public class MyMemberListModel extends AbstractListModel<String> {
	ArrayList<MemberTO> memList;
	
	public MyMemberListModel(String userId) {
		// TODO Auto-generated constructor stub
		ExManaDAO dao = new ExManaDAO();
		memList = dao.searchMyMember(userId);
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return memList.size();
	}
	
	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		MemberTO to = memList.get(index);
		String result = String.format("%s %s %s %scm %skg %s", to.getMname(), to.getGender(), to.getMcontact(), to.getHeight(), to.getWeight(), to.getMserial());
		
		return result;
	}
}
