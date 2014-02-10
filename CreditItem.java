package dusty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class CreditItem {

	int item_id;
	int customer_id;
	int user_id;
//	int months=1;
	String strAmount;
	String descr;
	double debit=0;

//	DateTime expJodaDate;
//	Date expJavaDate;
//	String strDate; //for use in "mere" display
	String nextAction;
	String goSearchAction;
//	String catName;
//	String vendorName;
	protected int credittype;  // 1=misc    2=romance
	protected int debittype;  // 1=misc    2=romance
	protected int itemID;
	protected  BigDecimal amount;
//	public List<SelectItem> expcats;
//	public List<SelectItem> vendors;

	
	public CreditItem(){
System.out.println("45 CreditItem()");	
	String param = (String)FacesUtil.getRequestParam("customer_id");
	if (param!=null) {this.customer_id = Integer.parseInt(param);}
	
	Customer cbean = (Customer)FacesUtil.getSessionBean("customer");
	if (cbean !=null){this.customer_id=cbean.getCustomer_id(); } 

	
	
System.out.println("48 CreditItem() customer_id = " + param);	

	param = (String)FacesUtil.getRequestParam("credittype");
if (param!=null) {this.credittype = Integer.parseInt(param);}

param = (String)FacesUtil.getRequestParam("descr");
if (param!=null) {this.descr = param;}

param = (String)FacesUtil.getRequestParam("amount");
if (param!=null) {   //    this.amount = Integer.parseInt(param);}
	double fBalance = Float.parseFloat(param);
	this.amount= BigDecimal.valueOf(fBalance);
	}

	Userbean ubean = (Userbean)FacesUtil.getSessionBean("userbean");
	if (ubean !=null){this.user_id=ubean.getUser_id(); } 

	System.out.println("52 CreditItem() user_id = " + user_id);	
	}

	
	
	// constructor for "mere" display:
/*
	public Expense(	int expID, int catID,double amnt,
					String vndr,String dscr, String strDate){
			this.expense_id=expID;
			this.cat_id	=catID;
			this.amount		=amnt;
			this.vendorName		=vndr;
			this.descr		=dscr;
			this.strDate 	= strDate;
	}
*/
	// constructor for "mere" display:
	public CreditItem(	int itemID, 
					int catID, 
					double amnt,
					String vndr,
					String dscr, 
					String strDate,
					String strCat){
			this.item_id=itemID;
		
			
			DecimalFormat df = new DecimalFormat("0.00");
	        this.strAmount =df.format(amnt);
			
			this.descr		=dscr;
	}
	
	

		public String delete(){
		String strSQL="call voidCredit("+	this.item_id + ");" ;
		DBUtils.executeSQL(strSQL);
	
		return "customer";
	}
	
	public void saveCredit(ActionEvent event){
System.out.println("113 saveCredit customer_id=" + this.customer_id);

//saveCredit(IN user_id int, IN amt float, IN descr varchar(100), IN cust int, IN type int) 
		this.descr= this.descr.replace("'","''");
			String strSQL="call saveCredit("+	
					this.user_id 	 + " ," +
					this.amount 	 + " ,'" + 
					this.descr 		 + "'," + 
					this.customer_id + "," +
					this.credittype  + ");" ;
		    DBResults rs = DBUtils.insertSQL(strSQL);
		
		if (rs.getRowSize() > 0 && rs.getElement(1,1) !=null ){
			this.item_id = Integer.parseInt(rs.getElement(1,1));
		   }
		if (this.credittype == 1){
			strSQL="call increaseRegularBalance(" +	
					this.customer_id + ","  + 
					this.amount + ");";		
			DBUtils.executeSQL(strSQL);
			}
		
		else if (this.credittype==2){
			
			strSQL="call increaseRomanceBalance(" +	
					this.customer_id + ","  + 
					this.amount + ");";		
		DBUtils.executeSQL(strSQL);
		}
System.out.println("132 saveCredit sql=" + strSQL);
		
	}
	
	
	public void saveDebit(ActionEvent event){
System.out.println("158 saveDebit customer_id=" + this.customer_id);
// saveCredit(IN user_id int, IN amt float, IN descr varchar(100), IN cust int, IN type int) 
		this.descr= this.descr.replace("'","''");
			String strSQL="call saveDebit("+	
					this.user_id 	 + " ," +
					this.amount 	 + " ,'" + 
					this.descr 		 + "'," + 
					this.customer_id + "," +
					this.credittype  + ");" ;
		    DBResults rs = DBUtils.insertSQL(strSQL);
		
		if (rs.getRowSize() > 0 && rs.getElement(1,1) !=null ){
			this.item_id = Integer.parseInt(rs.getElement(1,1));
		   }
		if (this.credittype == 1){
			strSQL="call decreaseRegularBalance(" +	
					this.customer_id + ","  + 
					this.amount + ");";		
			DBUtils.executeSQL(strSQL);
			}
		else if (this.credittype==2){
			
			strSQL="call decreaseRomanceBalance(" +	
					this.customer_id + ","  + 
					this.amount + ");";		
		DBUtils.executeSQL(strSQL);
		}
System.out.println("186 saveCredit sql=" + strSQL);
		
	}
		
	public String getNextAction(){
System.out.println("Expense 151 getNextAction()");		
		
		return "confirmExp";
	}

	public String nextAction(){
		
		return "confirmExp";
	}
	
	
	public String getGoSearchAction(){
		
		return "expSearch";
	}

	public String goSearchAction(){
		
		return "expSearch";
	}

	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}




	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public void setGoSearchAction(String goSearchAction) {
		this.goSearchAction = goSearchAction;
	}





	public String getStrAmount() {
		return strAmount;
	}


	public void setStrAmount(String strAmount) {
		this.strAmount = strAmount;
	}


	public double getDebit() {
		return debit;
	}


	public void setDebit(double debit) {
		this.debit = debit;
	}


	public int getCredittype() {
		return credittype;
	}


	public void setCredittype(int credittype) {
		this.credittype = credittype;
	}




	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	
	
}
