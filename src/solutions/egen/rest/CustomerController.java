package solutions.egen.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.egen.dao.CustomerDAO;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Customer;

@Path("/customer")
public class CustomerController {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		
		AppResponse appResponse = new AppResponse();
		
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			List<Customer> reservations = customerDAO.getCustomerDetails();
			appResponse.setPayload(reservations);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}

}
