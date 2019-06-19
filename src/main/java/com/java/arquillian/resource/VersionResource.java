package com.java.arquillian.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.java.arquillian.util.VersionUtil;

@Path("/version")
@Produces(MediaType.APPLICATION_JSON)
public class VersionResource {

	private static final String ARTIFACT_ID = "java-arquillian";
    private static final String GROUP_ID = "java-arquillian";

    @GET
	@Path("/")
	public Response find() {
		final VersionUtil versionUtil = new VersionUtil();
		Map<String, String> version = new HashMap<>();
		version.put(ARTIFACT_ID, versionUtil.getVersion(GROUP_ID, ARTIFACT_ID));
		return Response.ok().entity(version).build();
	}
}
