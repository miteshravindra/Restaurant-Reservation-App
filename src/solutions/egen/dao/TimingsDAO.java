package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.egen.exceptions.AppException;
import solutions.egen.model.Timings;
import solutions.egen.utils.DBUtil;

public class TimingsDAO {
	
	/*retrieves the detail of the restaurant timings
	 * returns : List of timings*/
	public List<Timings> getTimingsDetails() throws AppException{
		List<Timings> timings = new  ArrayList<Timings>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM schedule");
			rs = ps.executeQuery();
			while(rs.next()){
				
				Timings timing = new Timings();
				timing.setDay(rs.getString("day"));
				timing.setStartTime(rs.getString("starttime"));
				timing.setEndTime(rs.getString("endtime"));
				timing.setStatus(rs.getBoolean("status"));
				timings.add(timing);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return timings;
	}

}
