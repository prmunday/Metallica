package com.metallica.NewRefService.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="counterparty")
public class CounterParty {
	
	@Id
	private String id;
	private String name;
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
	@Override
	public String toString() {
		return "CounterParty [id=" + id + ", name=" + name + "]";
	}
	public CounterParty(String id,String name) {
		super();
		this.id=id;
		this.name = name;
	}
	public CounterParty() {
		super();
	}
	

}
