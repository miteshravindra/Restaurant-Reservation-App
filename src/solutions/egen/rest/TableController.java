package solutions.egen.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.egen.dao.TableDAO;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Table;

@Path("/table")
public class TableController {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getTablesDetail(){
		AppResponse appResponse = new AppResponse();
		try {
			TableDAO tableDAO = new TableDAO();
			List<Table> tables = tableDAO.getTablesDetail();
			appResponse.setPayload(tables);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	
	@PUT
	@Path("/reserve")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse reserveTable(Table table){
		AppResponse appResponse = new AppResponse();
		try{
			TableDAO tableDAO = new TableDAO();
			boolean result = tableDAO.reserveTable(table);
			if(result){
				appResponse.setMessage("Table is successfully reseved");
			}
			else{
				appResponse.setMessage("Problem in reserving the table");
			}
		}
		catch(AppException e){	
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	

}
