package com.java.arquillian.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class RestRequestFilter implements ContainerRequestFilter {
    private static final Logger LOGGER = Logger.getLogger(RestRequestFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        String path = requestCtx.getUriInfo().getPath();
        LOGGER.log(Level.INFO, () -> "Filtering request path: " + path);
    }
}
