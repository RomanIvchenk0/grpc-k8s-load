package com.github.romahat.load;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/load")
public class LoadResource {
    private final LoadService loadService;

    public LoadResource(LoadService loadService) {
        this.loadService = loadService;
    }

    @GET
    @Path("/count/" + "{iterationCount}")
    public String iterationCount(@PathParam("iterationCount") Integer iterationCount) {
        loadService.setIterationCount(iterationCount);
        return null; //TODO replace this stub to something useful
    }

    @GET
    @Path("/length/" + "{iterationLength}")
    public String iterationLength(@PathParam("iterationLength") Integer iterationLength) {
        loadService.setIterationLength(iterationLength);
        return null; //TODO replace this stub to something useful
    }

    @GET
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Status status() {
        return loadService.status();
    }


}
