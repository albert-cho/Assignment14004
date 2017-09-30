package server.logic.model;

public class User {
	static int userid = 0;
	static String username = "John";
	static String password = "Password";
	
	public User(int userid,String username, String password){
		this.userid=userid;
		this.username=username;
		this.password=password;
	}
	
	public String toString(){
		return "["+this.userid+","+this.username+","+this.password+"]";
	}
		
	public static int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid){
		this.userid=userid;
	}

	public static String getUsername() {
		return username;
	}
	
	public void setUsername(String username){
		this.username=username;
	}

	public static String getPassword() {
		return password;
	}
	
	public void setPassword(String password){
		this.password=password;
	}

}
