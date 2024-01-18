package com.edsonlacerda.control;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("check")
public class ControlListen {

    @Inject
    @RestClient
    TableService tableService;

    @GET
    @Path("/changes")
    //@Scheduled(every="5s")
    public List<Tables> checkForChanges() {
        return tableService.findTableFree();
    }

}
