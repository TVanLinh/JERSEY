package service;

import entity.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhtran on 10/09/17.
 */
@Path(value = "/person")
public class PersonRestfulService {

    @GET
    @Path("{firstName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam(value = "firstName") String firstName) {
        Person person = new Person();
        person.setFirtName("Tran");
        person.setLastName("Van Linh");
        return person;
    }

    @GET
    @Path("/get-persons")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person>  getPerson() {
        List<Person> list =new ArrayList<Person>();
        Person person = new Person();
        person.setFirtName("Tran");
        person.setLastName("Van Linh");
        list.add(person);
        list.add(person);
        list.add(person);
        list.add(person);
        list.add(person);
        return list;
    }


    @GET
    @Path("/person-xml")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person>  getPersons() {
        List<Person> list =new ArrayList<Person>();
        Person person = new Person();
        person.setFirtName("Tran");
        person.setLastName("Van Linh");
        list.add(person);
        list.add(person);
        list.add(person);
        list.add(person);
        list.add(person);
        return list;
    }
}
