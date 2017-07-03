package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Login;
import solutions.egen.utils.DBUtil;

public class LoginDAO {
	
	/*retrive username and password from the login table*/
	public Login authenticate() throws AppException{
		Login login = new Login();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM login");
			rs = ps.executeQuery();
			while(rs.next()){
				login.setEmail(rs.getString("email"));
				login.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return login;
	}

}
