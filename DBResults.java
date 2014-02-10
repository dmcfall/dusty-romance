package dusty;


import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean     //(eager=true)
@SessionScoped
public class DBResults {
        ArrayList<ArrayList> rows;

		public DBResults() {
			super();
			rows = new ArrayList<ArrayList>();		
		}
		
		public DBResults(ArrayList<ArrayList> rows) {
			super();
			this.rows = rows;
		}
		
		public void addRow(ArrayList<String> row){
			rows.add(row);
		}
		
		public String getElement(int row,int col){
			if(row <= rows.size()){
			    ArrayList<String> r = rows.get(row - 1);
			    if(col <= r.size())
			    	return r.get(col - 1);
			    else 
			    	return null;
			}
			return null;
		}
		
		public ArrayList getRows(){
			return rows;
		}
			
		
		public int getRowSize(){
			return rows.size();
		}
		
		
		/*caller method must make sure that the columns of both the result sets match*/
		public void appendRows(DBResults rs){
				this.rows.addAll(rs.getRows());					
		}
		
		
		
}
