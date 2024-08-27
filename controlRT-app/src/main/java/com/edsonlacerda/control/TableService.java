package com.edsonlacerda.control;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;
import java.util.List;

//@RegisterRestClient(baseUri = "http://localhost:8082/tables")
@RegisterRestClient(baseUri = "http://table-app-edson-lacerda-dev.apps.sandbox-m2.ll9k.p1.openshiftapps.com/tables")
public interface TableService {

    @GET
    @Path("/findTableFree")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000,
            successThreshold = 2
    )
    List<Tables> findTableFree();

    @PUT
    @Path("/occupy/{id}/{phoneNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000,
            successThreshold = 2
    )
    Response occupyTable(Long id, Long phoneNumber);

    default List<Tables> fallback() {
        return null;
    }
}
