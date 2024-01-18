package com.edsonlacerda.control;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8082/reservation")
public interface ReservationService {

}
