package com.edsonlacerda.reservation;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.ws.rs.core.Response;

@Path("reservation")
public class ReservationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> reservations(){
        return Reservation.listAll();
    }

    @GET
    @Path("/findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation findById(@QueryParam("id") Long id){
        return Reservation.findById(id);
    }

    @GET
    @Path("/findByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation findByName(@QueryParam("name") String name){
        return null;
    }

    @GET
    @Path("/findByPhoneNumber")
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation findByPhoneNumber(@QueryParam("phoneNumber") Long phoneNumber){
        return null;
    }
    @Transactional
    @DELETE
    @Path("/deleteReservation/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReservation(@PathParam("id") Long id) {
        Reservation reservation = Reservation.findById(id);
        if (reservation != null) {
            reservation.delete();
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation newReservation(ReservationDTO reservationDto){
        //prepareSMS();
        String twilioPhoneNumber = "+12062073830";
        String destinatario = "+19993431002";
        String mensagem = "Sua Reserva foi efetuada!";
        sendSMS(twilioPhoneNumber, destinatario, mensagem);

        //perguntar se recebeu sms ou zap

        Reservation reservation = new Reservation();
        reservation.id = null;
        reservation.setName(reservationDto.getName());
        reservation.setPhoneNumber(reservationDto.getPhoneNumber());
        reservation.setNumberOfPeople(reservationDto.getNumberOfPeople());
        reservation.persist();
        return reservation;
    }

    private static void prepareSMS() {
        //Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String twilioPhoneNumber = "+12062073830";
        String destinatario = "+19993431002";
        String mensagem = "Sua Reserva foi efetuada!";
        sendSMS(twilioPhoneNumber, destinatario, mensagem);
    }

    public static void sendSMS(String remetente, String destinatario, String mensagem) {
        Message mensagemEnviada = Message.creator(
                    new PhoneNumber(destinatario),
                    new PhoneNumber(remetente),
                    mensagem)
                .create();
        System.out.println("Mensagem enviada com sucesso. SID: " + mensagemEnviada.getSid());
    }

}
