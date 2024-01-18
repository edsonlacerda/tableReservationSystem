package com.edsonlacerda.reservation;

public class ReservationDTO {

    private Long phoneNumber;
    private String name;
    private Integer numberOfPeople;

    public ReservationDTO(Long phoneNumber, String name, Integer numberOfPeople) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.numberOfPeople = numberOfPeople;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
