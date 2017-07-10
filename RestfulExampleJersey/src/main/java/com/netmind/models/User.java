package com.netmind.models;

//@XmlRootElement(name="user")
public class User {
    //@XmlElement(name="name")
	private int uid;
    private String name;
    private String surname;
    private String email;
    
    public User(){
    	
    }
    
	public User(int uid, String name, String surname, String email) {
		super();
		this.uid = uid;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}