package server.logic.model;

public class User {
	int userid;
	String username;
	String password;
	
	public User(int userid,String username, String password){
		this.userid=userid;
		this.username=username;
		this.password=password;
	}
	
	public String toString(){
		return "["+this.userid+","+this.username+","+this.password+"]";
	}
		
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid){
		this.userid=userid;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}

}
