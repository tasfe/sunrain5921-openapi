package org.sunrain.openapi.model;

public class City {
	
	public String id;
	public String code;
	public String name;
	
	public City(){}
	
	public City(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
