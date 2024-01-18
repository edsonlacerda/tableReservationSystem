package com.edsonlacerda.control;

import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    //@RestClient
    //@Inject
    //ReservationService reservationService;
    @RestClient
    @Inject
    TableService tableService;

    @Override
    public HealthCheckResponse call() {
        return null;
    }
}
