package com.sapient.marketdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="market_data")
public class MarketDataEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	Integer id;
	
	@Column(name = "name")
	String name;
	@Column(name = "identifier")
	String identifier;
	@Column(name = "price")
	double price;
	
	public MarketDataEntity() {
		super();
	}

	public MarketDataEntity(String name, String identifier, double price) {
		super();
		this.name = name;
		this.identifier = identifier;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MarketDataEntity [name=" + name + ", identifier=" + identifier + ", price=" + price + "]";
	}
	
	
	
	
	

}
