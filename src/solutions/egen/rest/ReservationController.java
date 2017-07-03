package solutions.egen.rest;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.egen.dao.ReservationDAO;
import solutions.egen.exceptions.AppException;
import solutions.egen.model.Reservation;

@Path("/reservation")
public class ReservationController {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		
		AppResponse appResponse = new AppResponse();
		
		try {
			ReservationDAO dao = new ReservationDAO();
			List<Reservation> reservations = dao.getAll();
			appResponse.setPayload(reservations);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	
	@GET
	@Path("/update/{cnfCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse updateReservation(@PathParam("cnfCode") int cnfCode){
		AppResponse appResponse = new AppResponse();
		try {
			ReservationDAO dao = new ReservationDAO();
			Reservation reservation = dao.updateReservation(cnfCode);
			appResponse.setPayload(reservation);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse createProfile(Reservation reservation){
		AppResponse appResponse = new AppResponse();
		try{
			ReservationDAO reservationDAO = new ReservationDAO();
			reservation = reservationDAO.createReservation(reservation);
			appResponse.setMessage("Reservation is done successfully");
			appResponse.setPayload(reservation);
		}
		catch(AppException e){	
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}

	

	@DELETE
	@Path("/delete/{cnfCode}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse deleteProfile(@PathParam("cnfCode") int cnfCode){
		AppResponse appResponse = new AppResponse();
		boolean result = false;
		try{
			ReservationDAO reservationDAO = new ReservationDAO();
			result = reservationDAO.deleteReservation(cnfCode);
			if(result){
				appResponse.setMessage("Reservation is successfully deleted");
			}
			else{
				appResponse.setMessage("Reservation could not be deleted");
			}
		}
		catch(AppException e){
			e.printStackTrace();
			appResponse.setStatus(AppResponse.ERROR);
			appResponse.setMessage(e.getMessage());
		}
		return appResponse;
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse updateReservation(Reservation reservation){
		AppResponse appResponse = new AppResponse();
		boolean result = false;
		try{
			ReservationDAO reservationDAO = new ReservationDAO();
			result = reservationDAO.updateReservation(reservation);
			if(result){
				appResponse.setMessage("Reservation is successfully updated");
			}
			else{
				appResponse.setMessage("Reservation could not be updated");
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
 