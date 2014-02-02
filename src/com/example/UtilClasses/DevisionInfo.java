package com.example.UtilClasses;

public class DevisionInfo {

	private String devision_name;
	private String id;
	//_________________________________________________________________
	public DevisionInfo(String d_name,String id){
		this.devision_name =d_name;
		this.id=id;
	}
	public DevisionInfo(){
		
	}
	public DevisionInfo (String d_name) {
		this.devision_name= d_name;
	}
	
	//______________________________________________________________________setter and getter
	public String getDevision_name() {
		return devision_name;
	}
	public void setDevision_name(String devision_name) {
		this.devision_name = devision_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
