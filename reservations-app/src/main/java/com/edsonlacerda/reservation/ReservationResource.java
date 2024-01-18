package com.edsonlacerda.reservation;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Path("reservation")
public class ReservationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> reservations(){
        return Reservation.listAll();
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation findById(@QueryParam("id") Long id){
        return Reservation.findById(id);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reservation newReservation(ReservationDTO reservationDto){
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

    public static void sendSMS(String remetente, String destinatario, String mensagem) {
        Message mensagemEnviada = Message.creator(
                    new PhoneNumber(destinatario),
                    new PhoneNumber(remetente),
                    mensagem)
                .create();
        System.out.println("Mensagem enviada com sucesso. SID: " + mensagemEnviada.getSid());
    }

}
