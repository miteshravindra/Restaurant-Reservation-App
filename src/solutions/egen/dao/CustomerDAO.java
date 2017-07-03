package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.exceptions.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Profile;
import solutions.egen.model.Reservation;
import solutions.egen.utils.DBUtil;

public class CustomerDAO {
	
	
	/*get all the customers*/
	public List<Customer> getCustomerDetails() throws AppException{
		List<Customer> customers = new  ArrayList<Customer>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM customer");
			rs = ps.executeQuery();
			while(rs.next()){
				Customer customer = new Customer();
				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching customers records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return customers;
	}

}
