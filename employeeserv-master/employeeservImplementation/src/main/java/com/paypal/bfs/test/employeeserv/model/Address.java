
package com.paypal.bfs.test.employeeserv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ADDRESS")
public class Address implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
	
	@NotNull(message="line1 is required, cannot be null")
	private String line1;
    
	private String line2;
    
	@NotNull(message="city is required, cannot be null")
	private String city;
	
	@NotNull(message="state is required, cannot be null")
    private String state;
	
	@NotNull(message="country is required, cannot be null")
    private String country;
	
	@NotNull(message="zipcode is required, cannot be null")
    private Integer zipcode;
    
    public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
	 @OneToOne(mappedBy = "address")
	 private Employee employee;

}
