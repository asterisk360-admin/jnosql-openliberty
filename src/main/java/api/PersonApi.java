package api;


import java.util.List;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PersonDAO;
import model.Person;


@PermitAll
@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonApi {
	
	
	@Inject
	private PersonDAO personDAO;
	
	
	@POST
	@Path("/")
	public Person savePerson(Person person) {
		return personDAO.save(person);
    	}
	
	
	@GET
	@Path("/{id}")
	public Person findPersonById(@PathParam("id") Long id){
		return personDAO.findById(id);
	}
	
	
	@GET
	@Path("/name/{name}")
	public List<Person> findByName(@PathParam("name") String name){
		return personDAO.findByName(name);
	}
	
	@GET
	@Path("/phone/{phone}")
	public List<Person> findByPhone(@PathParam("phone") String phone){
		return personDAO.findByPhone(phone);
	}
	
		
}
