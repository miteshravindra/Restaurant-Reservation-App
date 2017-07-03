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

public class ProfileDAO {
	
	/*update the profile details of the restaurant like name, address and email
	 * arguments : Profile
	 * return    : Profile
	 * */
	public Profile updateProfile(Profile profile) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rowcount = 0;
		boolean result = false;
		try {
			ps = con.prepareStatement("UPDATE restaurantprofile SET NAME = ?, PHONE =?, EMAIL = ?,ADDRESS = ?");
			ps.setString(1, profile.getName());
			ps.setString(2, profile.getPhone());
			ps.setString(3, profile.getEmail());
			ps.setString(4, profile.getAddress());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rowcount = ps.getUpdateCount();
			if(rowcount > 0){
				result = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching record from the database",e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return profile;
	}
	
	/*retrieves the profile details of the restaurant*/
	public Profile getProfile() throws AppException{
		Profile profile = new Profile();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM restaurantprofile");
			rs = ps.executeQuery();
			while(rs.next()){
				profile.setName(rs.getString("name"));
				profile.setPhone(rs.getString("phone"));
				profile.setEmail(rs.getString("email"));
				profile.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching profile records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return profile;
	}

}
