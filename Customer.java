package dusty;

import java.math.BigDecimal;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import dusty.DBResults;
import dusty.DBUtils;

import dusty.FacesUtil;

@ManagedBean     //(eager=true)
@SessionScoped
public class Customer {

	public	int	customer_id;
	public	int	user_id;
	
	public String lastName;
	public String firstName;
	public String searchName;
	public BigDecimal regular_balance;
	public BigDecimal romance_balance;
	
	public double displayRegBalance;
	public double displayRomBalance;

	public boolean alreadyInit ;
	public boolean resetCustomerForNew;
	
	public Collection<BigDecimal> listOfCredits;
	public Collection<BigDecimal> listOfDebits;

	
	
	
	public Customer(){
		String param= (String)FacesUtil.getRequestParam("customer_id");
		if(param != null)
			{this.customer_id = Integer.parseInt(param);}
System.out.println("38 Customer  customer_id="+customer_id);
   		param= (String)FacesUtil.getRequestParam("searchName");
   		if(param != null)
   			{this.searchName = param;}
	}
	
	public Customer(int expID,
					BigDecimal balance, 
					String firstname, 
					String lastname, 
					BigDecimal creditRegBal,
					BigDecimal creditRomBal){
		this.firstName = firstname;
		this.lastName = lastname;
		this.searchName = this.lastName + ", " + this.firstName;
		this.regular_balance = creditRegBal;
		this.romance_balance = creditRomBal;
		
			};
	
	
			public void init() {
System.out.println("53 Customer  init()");
				
				String query = "call getCustomerDetails(" + this.customer_id + ");";
				DBResults rs = DBUtils.selectSQL(query);
//select firstName, lastName, regular_balance, romance_balance  from customers where customer_id = id;
// 		double tempFloat = 0;
				if (rs.getRowSize()> 0){
					this.firstName= rs.getElement(1, 1);
					this.lastName = rs.getElement(1, 2);
					this.searchName = this.lastName + ", " + this.firstName;
								
					if(rs.getElement(1, 3) != null)
					  this.displayRegBalance = Float.parseFloat(rs.getElement(1, 3));
                      this.regular_balance = BigDecimal.valueOf(this.displayRegBalance);
                      
  					if(rs.getElement(1, 4) != null)
  					  this.displayRomBalance = Float.parseFloat(rs.getElement(1, 4));
                        this.romance_balance = BigDecimal.valueOf(this.displayRomBalance);

                      
System.out.println("65 Customer init() regular_balance:" + this.displayRegBalance);
                      
				}

				alreadyInit=true;
		}
			
			public void deleteCustomer(ActionEvent event){
				String strSQL="call deleteCustomer'"+ this.customer_id  + ");" ;
System.out.println("80 saveNewCustomer sql=" + strSQL);
				DBUtils.executeSQL(strSQL);
System.out.println("84 saveNewCustomer sql=" + strSQL);
				}
			
			public void saveNewCustomer(ActionEvent event){
System.out.println("95 saveNewCustomer");
				// saveNewCustomer(IN fn varchar(45), IN ln varchar(45)) 
						this.lastName= this.lastName.replace("'","''");
							String strSQL="call saveNewCustomer('"+	
									this.firstName	+ "','" + 
									this.lastName 	+ "');" ;
System.out.println("103 saveNewCustomer sql=" + strSQL);

							DBResults rs = DBUtils.insertSQL(strSQL);
						
						if (rs.getRowSize() > 0 && rs.getElement(1,1) !=null ){
							  this.customer_id = Integer.parseInt(rs.getElement(1,1));
						      }
				System.out.println("109 saveNewCustomer sql=" + strSQL);
						
					}			
			
			
			
		public void deleteCustomer(){
			System.out.println("77 Customer  getLastName:" + lastName);
			
		}
			
		public String addCustomer(){
		//CsREATE PROCEDURE addCustomer(IN fname varchar(45), IN lname varchar(45)) 
		
		return "customer";
	}	
		
		
		
	public String getLastName() {
System.out.println("77 Customer  getLastName:" + lastName);
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
System.out.println("83 Customer  getFirstName:" + firstName);
		
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Collection<BigDecimal> getListOfCredits() {
		return listOfCredits;
	}
	public void setListOfCredits(Collection<BigDecimal> listOfCredits) {
		this.listOfCredits = listOfCredits;
	}
	public Collection<BigDecimal> getListOfDebits() {
		return listOfDebits;
	}
	public void setListOfDebits(Collection<BigDecimal> listOfDebits) {
		this.listOfDebits = listOfDebits;
	}


	public int getCustomer_id() {
//System.out.println("103 Customer  getCustomer_id:" + customer_id);
	//	if (!alreadyInit) {
System.out.println("105 Customer  getCustomer_id:" + customer_id);
			init();
//System.out.println("107 Customer  getCustomer_id:" + customer_id);
		//}
//System.out.println("109 Customer  getCustomer_id:" + customer_id);
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public boolean isAlreadyInit() {
		return alreadyInit;
	}


	public void setAlreadyInit(boolean alreadyInit) {
		this.alreadyInit = alreadyInit;
	}


	public String getSearchName() {
		return searchName;
	}


	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}


	public BigDecimal getRegular_balance() {
		return regular_balance;
	}


	public void setRegular_balance(BigDecimal regular_balance) {
		this.regular_balance = regular_balance;
	}


	public BigDecimal getRomance_balance() {
		return romance_balance;
	}


	public void setRomance_balance(BigDecimal romance_balance) {
		this.romance_balance = romance_balance;
	}


	public double getDisplayRegBalance() {
		return displayRegBalance;
	}


	public void setDisplayRegBalance(double displayRegBalance) {
		this.displayRegBalance = displayRegBalance;
	}


	public double getDisplayRomBalance() {
		return displayRomBalance;
	}


	public void setDisplayRomBalance(double displayRomBalance) {
		this.displayRomBalance = displayRomBalance;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public boolean isResetCustomerForNew() {
		firstName="";
		lastName="";
		searchName="";
	return resetCustomerForNew;
	}


	public void setResetCustomerForNew(boolean resetCustomerForNew) {
		firstName="";
		lastName="";
		searchName="";
		this.resetCustomerForNew = resetCustomerForNew;
	}


	
	
}
