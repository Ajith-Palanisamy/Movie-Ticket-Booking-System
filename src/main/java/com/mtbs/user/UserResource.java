package com.mtbs.user;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mtbs.theater.Theater;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("user")
public class UserResource 
{
	UserDAO userDao=new UserDAO();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> getUsers()
	{
		return userDao.getUsers();
	}
	
	@GET
	@Path("/manager")
	@Produces({MediaType.APPLICATION_JSON})
	public String getManagers()
	{
		return userDao.getManagers().toString();
	}
	
	@GET
	@Path("/{user_id}/bookings")
	@Produces({MediaType.APPLICATION_JSON})
	public String getBookings(@PathParam("user_id") int user_id)
	{
		return userDao.getBookings(user_id);
	}
	
	@GET
	@Path("/{user_id}/theater")
	@Produces({MediaType.APPLICATION_JSON})
	public String getTheaters(@PathParam("user_id") int user_id)
	{
		return userDao.getTheaters(user_id).toString();
	}
	
	@POST
	@Path("/{user_id}/bookings/{booking_id}/cancel")
	public String cancelBooking(@PathParam("user_id") int user_id,@PathParam("booking_id") int booking_id,String jsonObject)
	{
		JSONObject jsonData=null;
		try {
			jsonData = (JSONObject)new JSONParser().parse(jsonObject);
		} catch (ParseException e) 
		{
			System.out.println("Exception in bookTicket() "+e);
		}
		return userDao.cancelBooking(booking_id,user_id,jsonData);
	}
}
