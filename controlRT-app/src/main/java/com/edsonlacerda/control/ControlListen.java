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

    @Inject
    @RestClient
    ReservationService reservationService;

    @GET
    @Path("/changes")
    public List<Tables> checkForChanges() {
        List<Tables> freeTables = tableService.findTableFree();
        List<Reservation> reservation = reservationService.reservations();
        if (!freeTables.isEmpty() && !reservation.isEmpty()){
            List<Reservation> orderedReservations = reservation.stream()
                    .sorted((r1, r2) -> Long.compare(r1.getId(), r2.getId()))
                    .toList();

            List<Tables> orderedTables = freeTables.stream()
                    .sorted((m1, m2) -> Long.compare(m1.getId(), m2.getId()))
                    .toList();

            if (!orderedReservations.isEmpty() && !orderedTables.isEmpty()) {
                Reservation firstReservation = orderedReservations.get(0);
                Tables firstTable = orderedTables.get(0);
                tableService.occupyTable(firstTable.getId(), firstReservation.getId());
                reservationService.deleteReservation(firstReservation.getId());
            }
        }
        return freeTables;
    }


}
