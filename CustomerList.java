package dusty;


import java.util.Collection;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dusty.Customer;
import dusty.DBUtils;
import dusty.DBResults;

@ManagedBean     //(eager=true)
@SessionScoped
public class CustomerList {
   
	protected Customer selectedCustomer;
	public String searchName;

	protected Collection<CustomerListItem> searchcustomers;
	protected Collection<CustomerListItem> allcustomers;
	

	
	public CustomerList() {
		String param = (String)FacesUtil.getRequestParam("searchName");
		if (param!=null) {this.searchName= param;}
	System.out.println("CustomerList 28  searchName="+ searchName);

	}

	
	protected void init() {
		
			allcustomers= new ArrayList<CustomerListItem>();
		
			String strQuery="call getCustomerList()";
			DBResults rs = DBUtils.selectSQL(strQuery);
		
			String fName;
			String lName;
			int custID;
			int iLimit = rs.getRowSize();
			int count = 1;

			while (count < iLimit + 1) {
				custID = Integer.parseInt(rs.getElement(count, 1));
				fName = rs.getElement(count, 2);
				lName= rs.getElement(count, 3);
				allcustomers.add(new CustomerListItem(custID, fName, lName));
				count++;
				}
			

	}


//	==================================================================
	public String customersearch(){
		
System.out.println("89 CustomerList searchName=" + searchName);
		//if (customers != null) customers.clear();
		//if (searchcustomers==null) search
		searchcustomers= new ArrayList<CustomerListItem>();

		String query = "call findCustomers('%" + this.searchName + "%');";
		DBResults rs = DBUtils.selectSQL(query);
//		CREATE PROCEDURE findCustomers(IN prm varchar(45)) 
//		select customer_id, firstName, lastName, regular_balance, romance_balance from customers where lastname like prm;
		
		String fName;
		String lName;
		int custID;
		double romBal;
		double regBal;
		int iLimit = rs.getRowSize();
		int count = 1;

		while (count < iLimit + 1) {
			custID = Integer.parseInt(rs.getElement(count, 1));
			fName = rs.getElement(count, 2);
			lName= rs.getElement(count, 3);
			regBal = Double.parseDouble(rs.getElement(count,4));
			romBal = Double.parseDouble(rs.getElement(count,5));
			
			searchcustomers.add(new CustomerListItem(custID, fName, lName));
			count++;
			}



return "customerlist";
	}

//	==================================================================

	
	
	
	
	protected void deleteCustomer(int custID){
		String strQuery = "call deleteCustomer(" + custID + ");";
		DBUtils.executeSQL(strQuery);
	//	customers.clear();
	//	customers= null;
	//	init();
	}


	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}


	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public Collection<CustomerListItem> getAllcustomers() {
		init();
		return allcustomers;
	}


	public void setAllcustomers(Collection<CustomerListItem> allcustomers) {
		this.allcustomers = allcustomers;
	}


	public Collection<CustomerListItem> getSearchcustomers() {
		return searchcustomers;
	}


	public void setSearchcustomers(Collection<CustomerListItem> searchcustomers) {
		this.searchcustomers = searchcustomers;
	}	
	
	
}
