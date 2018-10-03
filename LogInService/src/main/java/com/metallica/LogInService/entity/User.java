package com.metallica.LogInService.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user-info")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	String name;
	String username;
	Status status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String name, String username, Status status) {
		super();
		this.name = name;
		this.username = username;
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", username=" + username + ", status=" + status + "]";
	}
	
}
