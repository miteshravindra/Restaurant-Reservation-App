package solutions.egen.rest;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.egen.dao.TimingsDAO;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Timings;

@Path("/timings")
public class TimingsController {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getTimingsDetail(){
		AppResponse appResponse = new AppResponse();
		try {
			TimingsDAO timingsDAO = new TimingsDAO();
			List<Timings> timings = timingsDAO.getTimingsDetails();
			appResponse.setPayload(timings);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	
}
