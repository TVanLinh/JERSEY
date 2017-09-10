package client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import entity.Person;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by linhtran on 10/09/17.
 */
public class MyClient {
    public static void main(String[] args) {
//        Client client = Client.create();
//
//        WebResource webResource = client.resource("http://localhost:8080/rest/weather/hanoi");
//
//        ClientResponse clientResponse = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
//        if (clientResponse.getStatus() != 200) {
//            System.out.println("error");
//        } else {
//            System.out.println(clientResponse.getEntity(String.class));
//        }

        getPersonObject("Tran");
        List<Person> personList = getPersons();
        for (Person person : personList) {
            System.out.println(person);
        }
        System.out.println("xml----------------------------");
        personList = getPersonsXML();
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    public static Person getPersonObject(String firstName) {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);

        Client client = Client.create(defaultClientConfig);
        WebResource webResource = client.resource("http://localhost:8080/rest/person/" + firstName);

        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON)
                .header("content-type", MediaType.APPLICATION_JSON);
        ClientResponse clientResponse = builder.get(ClientResponse.class);
        if (clientResponse.getStatus() != 200) {
            System.out.printf("Error");
            return null;
        } else {
            Person person = clientResponse.getEntity(Person.class);
            System.out.println(person);
            return person;
        }
    }

    public static List<Person> getPersons() {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);

        Client client = Client.create(defaultClientConfig);

        WebResource webResource = client.resource("http://localhost:8080/rest/person/get-persons");

        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_JSON)
                .header("content-type", MediaType.APPLICATION_JSON);

        ClientResponse clientResponse = builder.get(ClientResponse.class);

        if (clientResponse.getStatus() != 200) {
            System.out.println("Failed with HTTP Error code: " + clientResponse.getStatus());
            return null;
        }
        GenericType<List<Person>> genericType = new GenericType<List<Person>>() {

        };
        List<Person> list = clientResponse.getEntity(genericType);
        return list;
    }


    public static List<Person> getPersonsXML() {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);

        Client client = Client.create(defaultClientConfig);

        WebResource webResource = client.resource("http://localhost:8080/rest/person/person-xml");

        WebResource.Builder builder = webResource.accept(MediaType.APPLICATION_XML)
                .header("content-type", MediaType.APPLICATION_XML);

        ClientResponse clientResponse = builder.get(ClientResponse.class);

        if (clientResponse.getStatus() != 200) {
            System.out.println("Failed with HTTP Error code: " + clientResponse.getStatus());
            return null;
        }
        GenericType<List<Person>> genericType = new GenericType<List<Person>>() {

        };
        List<Person> list = clientResponse.getEntity(genericType);
        return list;
    }
}

