package solutions.egen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Table;
import solutions.egen.utils.DBUtil;

public class TableDAO {
	
	/*retrieve the details of the table
	 * returns : List of Table*/
	public List<Table> getTablesDetail() throws AppException{
		List<Table> tables = new  ArrayList<Table>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM tables");
			rs = ps.executeQuery();
			while(rs.next()){
				
				Table table = new Table();
				table.setId(rs.getInt("id"));
				table.setCnfCode(rs.getInt("cnfcode"));
				table.setSince(rs.getString("since"));
				table.setSize(rs.getInt("size"));
				table.setStatus(rs.getString("status"));
				tables.add(table);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching records from database", e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return tables;
	}
	
	/*reserve the table selected for a given reservation
	 * arguments : Table table
	 *return: boolean result */
	public boolean reserveTable(Table table) throws AppException{
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			
			ps = con.prepareStatement("UPDATE tables SET SIZE = ?, CNFCODE = ?,SINCE =? ,STATUS =? WHERE ID = ?");
			ps.setInt(1, table.getSize());
			ps.setInt(2,table.getCnfCode());
			ps.setString(3, table.getSince());
			ps.setString(4, table.getStatus());
			ps.setInt(5, table.getId());
			ps.executeUpdate();
			if(ps.getUpdateCount()>0){
				result = true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Unable to assign table",e.getCause());
		}
		finally{
			DBUtil.closeResources(ps, rs, con);
		}
		return result;
	}
	

}
