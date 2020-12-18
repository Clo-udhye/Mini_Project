package model;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.table.AbstractTableModel;

import data.ExManaDAO;
import data.TScheduleTO;

public class RsvTableModel extends AbstractTableModel {
	ArrayList<TScheduleTO> datas = new ArrayList<TScheduleTO>();
	ArrayList<String> scheduledDate = new ArrayList<String>();

	public RsvTableModel(String userId, Calendar sCal, Calendar eCal) {
		// TODO Auto-generated constructor stub
		ExManaDAO dao = new ExManaDAO();
		ArrayList<TScheduleTO> scheduled = dao.searchTSchedule(userId, sCal, eCal);
		
		for(TScheduleTO to : scheduled) {
			scheduledDate.add(to.getDate());
		}
			
		eCal.add(Calendar.DATE, -1);
		for (Date date = sCal.getTime(); sCal.before(eCal); sCal.add(Calendar.DATE, 1), date = sCal.getTime()) {
			//System.out.println(date);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			String strDate = String.format("%02d-%02d-%02d",c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DATE));
			for(int time = 10; time<=18; time+=2) {
				TScheduleTO to = new TScheduleTO();		
				to.setTserial(scheduled.get(0).getTserial());
				to.setDate(strDate + " " + time + ":00");
				to.setMserial(scheduled.get(0).getMserial());

				datas.add(to);
			}
		}		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		String result = "";
		TScheduleTO to = datas.get(rowIndex);
		switch(columnIndex) {
		case 0:
			result = to.getDate().split(" ")[0];
			break;
		case 1:
			result = to.getDate().split(" ")[1];
			break;
		case 2:
			if(scheduledDate.contains(to.getDate())) {
				result = "예약 불가능";
			} else {
				result =  "예약 가능";
			}
			break;	
		}		
		//System.out.println(to.getDate().split(" ")[0] + " " + to.getDate().split(" ")[1]);
		return result;
	}

	//컬럼이름
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		String[] columnNames = {"날짜", "시간", "예약상황"};
		return columnNames[column];
	}

	//Editable Option
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		boolean[] columnEditables = new boolean[] {
				false, false, false
		};

		return columnEditables[columnIndex];
	}

}
