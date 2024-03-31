package com.edsonlacerda.table;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("tables")
public class TablesResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> tables(){
        List<Tables> allTables = Tables.listAll();
        return allTables.stream()
                .sorted((m1, m2) -> Long.compare(m1.getId(), m2.getId()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Tables findById(@QueryParam("id") Long id) {
        return Tables.findById(id);
    }

    @GET
    @Path("/findTableFree")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> findTableFree() throws InterruptedException {
        return Tables.list("status",Status.FREE.getDescricao());
        //return Tables.listAll();
    }

    @GET
    @Path("/findTableOccupied")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> findTableOccupied() throws InterruptedException {
        return Tables.list("status",Status.OCCUPIED.getDescricao());
    }

    @GET
    @Path("/findTableWaiting")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> findTableWaiting() throws InterruptedException {
        return Tables.list("status",Status.WAITING.getDescricao());
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> newTable(TablesDTO table){
        List<Tables> listTables = new ArrayList<>();
        for (int i = 0; i < table.getNumberOfTables(); i++) {
            Tables tables = new Tables();
            tables.setStatus(Status.OCCUPIED.getDescricao());
            tables.setNumberOfChairs(table.getNumberOfChairs());
            tables.persist();
            listTables.add(tables);
        }
        return listTables;
    }

    @Transactional
    @PUT
    @Path("/occupy/{id}/{idReservation}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response occupyTable(@PathParam("id") Long id, @PathParam("idReservation") Long idReservation){
        Tables tables = Tables.findById(id);
        if (tables == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tables.setStatus(Status.WAITING.getDescricao());
        tables.setIdReservation(idReservation);
        tables.persist();
        return Response.ok(tables).build();
    }

    @Transactional
    @PUT
    @Path("/freeTable/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response freeTable(@PathParam("id") Long id){
        Tables tables = Tables.findById(id);
        if (tables == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        tables.setStatus(Status.FREE.getDescricao());
        tables.persist();
        return Response.ok(tables).build();
    }
}
