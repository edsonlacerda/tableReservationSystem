package com.edsonlacerda.table;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("tables")
public class TablesResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> tables(){
        return Tables.listAll();
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Tables findById(@QueryParam("id") Long id) {
        return Tables.findById(id);
    }

    @GET
    @Path("/findTableFree")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> findTableFree() throws InterruptedException {
        //Thread.sleep(3000);
        return Tables.list("status","FREE");
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tables> newTable(TablesDTO table){
        List<Tables> listTables = new ArrayList<>();
        for (int i = 0; i < table.getNumberOfTables(); i++) {
            Tables tables = new Tables();
            tables.id = null;
            tables.setPhoneNumber(null);
            tables.setTableNumber(i);
            tables.setStatus(Status.FREE.getDescricao());
            tables.setNumberOfChairs(table.getNumberOfChairs());
            tables.persist();
            listTables.add(tables);
        }
        return listTables;
    }
}
