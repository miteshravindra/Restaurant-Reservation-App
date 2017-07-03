package solutions.egen.rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.egen.dao.ProfileDAO;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Profile;

@Path("profile")
public class ProfileController {

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse updateProfile(Profile profile){
		AppResponse appResponse = new AppResponse();
		
		try{
			ProfileDAO profileDAO = new ProfileDAO();
			profile = profileDAO.updateProfile(profile);
			appResponse.setMessage("Profile is successfully updated");
			appResponse.setPayload(profile);
		}
		catch(AppException e){
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		
		return appResponse;
	}
	
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		
		AppResponse appResponse = new AppResponse();
		
		try {
			ProfileDAO profileDAO = new ProfileDAO();
			Profile profile = profileDAO.getProfile();
			appResponse.setPayload(profile);
			appResponse.setMessage("Profile is successfully updated");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	
}
