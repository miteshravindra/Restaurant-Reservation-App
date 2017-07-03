package solutions.egen.model;

import java.sql.Date;


public class Reservation {
	
	private int cnfCode;
	private int partySize;
	private String specialNeeds;
	private Date date;
	private String time;
	private Customer customer;
	
	public int getPartySize() {
		return partySize;
	}
	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}
	public String getSpecialNeeds() {
		return specialNeeds;
	}
	public void setSpecialNeeds(String specialNeeds) {
		this.specialNeeds = specialNeeds;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCnfCode() {
		return cnfCode;
	}
	public void setCnfCode(int cnfCode) {
		this.cnfCode = cnfCode;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
