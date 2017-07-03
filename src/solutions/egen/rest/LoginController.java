package solutions.egen.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import solutions.egen.dao.LoginDAO;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Login;

@Path("owner")
public class LoginController {
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse authenticate(){
		
		AppResponse appResponse = new AppResponse();
		
		try {
			LoginDAO loginDAO = new LoginDAO();
			Login login = loginDAO.authenticate();
			appResponse.setPayload(login);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	

}
