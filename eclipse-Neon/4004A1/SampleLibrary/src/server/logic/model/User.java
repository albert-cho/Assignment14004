package server.logic.model;

public class User {
	static int userid = 0;
	static String username = "John";
	static String password = "Password";
	
	
	public String toString(){
		return "["+this.userid+","+this.username+","+this.password+"]";
	}
	
	public static int getUserid() {
		return userid;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

}
