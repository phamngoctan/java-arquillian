package com.java.arquillian.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
@Produces({ MediaType.APPLICATION_JSON })
public class EmployeeResource {

	public EmployeeResource() {
		System.out.println("Hey");
	}
	
	@GET
	@Path("/")
	public Response find() {
		return Response.ok().entity("Hello world!").build();
	}
	
	@GET
    @Produces("text/html")
    public String getHtml() {
        return "<html lang=\"en\"><body><h1>Hello, World!!</h1></body></html>";
    }

}
