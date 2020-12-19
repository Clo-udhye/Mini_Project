import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.table.AbstractTableModel;

public class RsvTableModel extends AbstractTableModel {
	ArrayList<TScheduleTO> datas;

	public RsvTableModel(String userId, Calendar sCal, Calendar eCal) {
		// TODO Auto-generated constructor stub
		ExManaDAO dao = new ExManaDAO();
		datas = dao.searchTSchedule(userId, sCal, eCal);
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
			result = "예약불가능";
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
