package eu.geoc.application;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import static javax.ws.rs.core.HttpHeaders.*;


/**
 * Copyright (C) 2016 Geotec. All right reserved.
 * Created by German Mendoza on 18/07/2016.
 */

@Provider
public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        //containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Origin, Accept, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        //containerResponseContext.getHeaders().add("Content-Type", );
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        containerResponseContext.getHeaders().add("Cache-Control", "no-cache");
    }
}