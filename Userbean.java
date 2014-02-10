package dusty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Types;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean     //(eager=true)
@SessionScoped
public class Userbean  implements Serializable{
	
	private static final long serialVersionUID = 6749140182150312769L;

	public int user_id;
	public String username;
	public String pw;
	public String newpw;
	public boolean changepw;
	
	public String firstname;
	public String lastname;
	public boolean loggedin;
	public String sessionID;
	public int loginresult=-5;
	public int userperms;
	
	
	
	public String navmenu_addUser;
	public String navmenu_viewUsers;
	public String navmenu_viewSched;
	public String navmenu_buildings;
	public String navmenu_addBuilding;
	
	public String login;
    
	
	public Userbean(){
System.out.println("11 Userbean()");		

	HttpSession aSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	
	if (aSession!=null){
System.out.println("32 Userbean() aSession");		
		this.sessionID= aSession.getId();
		} else {  
System.out.println("34 Userbean() NOT aSession");		
		}
	}

	
	   public String login(){
System.out.println("63 Userbean.login()");
		String returnValue="login"; //default    	  
		   
		if((username != null  )&& ( pw!= null)){
			   		
			String strSQL;
			DBResults rs=null;
			strSQL= "call loginCheck('"+ username + "','" + pw +  "')";
			rs = DBUtils.selectSQL(strSQL);
//  select firstname, lastname, username, perms, user_id from users where username = uname AND pw = passwd

System.out.println("80 loginCheck :  " + rs.getElement(1, 5));
			   			
			if((rs!= null ) &&( rs.getRowSize() > 0) && (rs.getElement(1, 1) != null)){
				this.loggedin=true; 
				this.firstname = rs.getElement(1, 1);
				this.lastname  = rs.getElement(1, 2);
				this.username  = rs.getElement(1, 3);
				this.userperms  = Integer.parseInt(rs.getElement(1, 4));
				this.user_id =    Integer.parseInt(rs.getElement(1, 5));
			   //   1   5    15	

System.out.println("91 loginCheck :  " + this.userperms);
				
				if(this.userperms==15){
			   		returnValue = "admin";
			   		} else {
			   		returnValue = "admin";
			   		}
			   	}// name/pw found
		  
			else {loginresult=0; returnValue="login";}
		
		} // if incoming username and pw are not blank
			 			    
System.out.println("101 Userbean perms:  " + this.userperms);
System.out.println("102 Userbean returnValue :  " + returnValue);
		   
		   	return returnValue;

	   } // method
		

	   
	   
	   
	   public String deltalogin(){
System.out.println("111 Userbean ");

		   String returnValue="login"; //default    	  
		   
		   if((username != null  )&& ( pw!= null) && (newpw!=null)   ){
System.out.println("116 Userbean ");
			   		
			   String strSQL;
			   DBResults rs=null;
			   strSQL= "call loginCheck('"+ username + "','" + pw +  "')";
			   rs = DBUtils.selectSQL(strSQL);
//  select firstname, lastname, username, perms, user_id from users where username = uname AND pw = passwd

			   if((rs!= null ) &&( rs.getRowSize() > 0) && (rs.getElement(1, 1) != null)){
				   this.loggedin=true; 
				   this.firstname = rs.getElement(1, 1);
				   this.lastname  = rs.getElement(1, 2);
				   this.username  = rs.getElement(1, 3);
				   this.userperms  = Integer.parseInt(rs.getElement(1, 4));
				   this.user_id =    Integer.parseInt(rs.getElement(1, 5));
			   //   1   5    15	
System.out.println("132 Userbean ");

				   if(this.userperms==15){
			   			returnValue = "admin";
			   			} else {
			   				returnValue = "viewschedule48";
			   			}

				   strSQL= "call updatePassword("+ this.user_id + ",'" + this.newpw +  "');";
System.out.println("140 Userbean: SQL" + strSQL);
				   DBUtils.executeSQL(strSQL);
System.out.println("142 Userbean ");
				   
			   		}// name/pw found
			   else {loginresult=0; returnValue="login";}
		   			} // if incoming username and pw are not blank
System.out.println("147 Userbean returnValue= " +returnValue);

		   return returnValue;
	   } // method

	   
	   
	   
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "loggedout.xhtml?faces-redirect=true";
	}
		       
	
    
    public String goVendors(){
System.out.println("88 Userbean.goVendors()");
    	return "vendors";
    	
    }

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getFirstname() {
System.out.println("159 Userbean getFirstName()=" + firstname);
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public int getLoginresult() {
		return loginresult;
	}

		public int getUserperms() {
		return userperms;
	}

		public void setLoggedin(boolean loggedin) {
			this.loggedin = loggedin;
		}

		public void setUserperms(int userperms) {
			this.userperms = userperms;
		}

	    public String getNavmenu_buildings(){
System.out.println("232 Userbean getNavmenu_buildings()");
	    	return "buildings";}
	    
	    public String getNavmenu_addBuilding(){
System.out.println("234 Userbean getNavmenu_addBuilding()");
	    	return "addBldg";}

	    public String getNavmenu_viewSched(){
System.out.println("236 Userbean getNavmenu_viewSched()");
	    	return "viewschedule48";}
	    
		public String getNavmenu_addUser(){			
System.out.println("249 Userbean getNavmenu_addUser()");
			return "addUser";

/*
			if ( this.loggedin )
			   {return "addUser"; } 
			else
			   {return "loglog"; } 
	    */

		}
	    
		public String getNavmenu_viewUsers(){
//			if ( this.loggedin ){
System.out.println("209 Userbean getNavmenu_viewUsers()");

			return "users"; } 
//			else
//			   {return "loglog"; } 


		public String getNewpw() {
			return newpw;
		}


		public void setNewpw(String newpw) {
			this.newpw = newpw;
		}


		public boolean isChangepw() {
			return changepw;
		}


		public void setChangepw(boolean changepw) {
			this.changepw = changepw;
		}


		public String getLogin() {
			//return login;
			return "admin";
		}


		public void setLogin(String login) {
			this.login = login;
		}


	
//		}
	  
    
	
	
}
