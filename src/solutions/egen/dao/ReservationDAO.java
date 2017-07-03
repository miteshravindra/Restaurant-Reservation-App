package solutions.egen.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.exceptions.AppException;
import solutions.egen.model.Customer;
import solutions.egen.model.Reservation;
import solutions.egen.utils.DBUtil;

public class ReservationDAO {
	
	public List<Reservation> getAll() throws AppException{
		List<Reservation> reservations = new  ArrayList<Reservation>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM reservation ,customer WHERE reservation.email=customer.email");
			rs = ps.executeQuery();
			while(rs.next()){
				Reservation reservation = new Reservation();
				reservation.setPartySize(rs.getInt("partysize"));
				reservation.setSpecialNeeds(rs.getString("specialneeds"));
				reservation.setDate(rs.getDate("date"));
				reservation.setTime(rs.getString("time"));
				reservation.setCnfCode(rs.getInt("cnfcode"));
				
				Customer customer = new Customer();
				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				reservation.setCustomer(customer);
				reservations.add(reservation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return reservations;
	}
	
	
	public Reservation updateReservation(int cnfCode) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation reservation = new Reservation();
		try {
			ps = con.prepareStatement("SELECT * FROM reservation ,customer WHERE reservation.email=customer.email AND reservation.cnfCode = ?");
			ps.setInt(1, cnfCode);
			rs = ps.executeQuery();
			while(rs.next()){
				reservation.setPartySize(rs.getInt("partysize"));
				reservation.setSpecialNeeds(rs.getString("specialneeds"));
				reservation.setDate(rs.getDate("date"));
				reservation.setTime(rs.getString("time"));
				reservation.setCnfCode(rs.getInt("cnfcode"));
				
				Customer customer = new Customer();
				customer.setName(rs.getString("name"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				reservation.setCustomer(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return reservation;
	}
	
	
	public Reservation createReservation(Reservation reservation) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("INSERT INTO reservation (PARTYSIZE, SPECIALNEEDS, DATE, TIME , EMAIL) VALUES (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, reservation.getPartySize());
			ps.setString(2, reservation.getSpecialNeeds());
			ps.setDate(3, reservation.getDate());
			ps.setString(4, reservation.getTime());
			ps.setString(5, reservation.getCustomer().getEmail());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()){
				reservation.setCnfCode(rs.getInt(1));
			}
			
			ps = con.prepareStatement("INSERT INTO customer (EMAIL, PHONE, NAME) VALUES (?,?,?)");
			ps.setString(1, reservation.getCustomer().getEmail());
			ps.setString(2, reservation.getCustomer().getPhone());
			ps.setString(3, reservation.getCustomer().getName());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Customer already exists but the reservation is done successfully",e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return reservation;
	}
	
	
	public boolean deleteReservation(int cnfCode) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rowcount = 0;
		boolean result = false;
		try {
			ps = con.prepareStatement("DELETE FROM reservation WHERE cnfcode = ?");
			ps.setInt(1, cnfCode);
			ps.executeUpdate();
			rowcount = ps.getUpdateCount();
			if(rowcount > 0){
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in deleting the record",e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return result;
	}
	
	
	public boolean updateReservation(Reservation reservation) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rowcount = 0;
		boolean result = false;
		try {
			ps = con.prepareStatement("UPDATE reservation SET PARTYSIZE = ?, SPECIALNEEDS = ?, DATE = ? , TIME = ? WHERE CNFCODE = ? AND EMAIL = ?");
			ps.setInt(1, reservation.getPartySize());
			ps.setString(2, reservation.getSpecialNeeds());
			ps.setDate(3, reservation.getDate());
			ps.setString(4, reservation.getTime());
			ps.setInt(5, reservation.getCnfCode());
			ps.setString(6, reservation.getCustomer().getEmail());
			ps.executeUpdate();
			rowcount = ps.getUpdateCount();
			if(rowcount > 0){
				result = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in updating the record",e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return result;
	}
	
	
}
