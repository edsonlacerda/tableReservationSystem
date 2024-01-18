package com.edsonlacerda.table;

public class TablesDTO {

    private Long phoneNumber;
    private Integer tableNumber;
    private Integer numberOfChairs;
    private Boolean status;
    private Integer numberOfTables;

    public TablesDTO(Long phoneNumber, Integer tableNumber, Integer numberOfChairs, Boolean status, Integer numberOfTables) {
        this.phoneNumber = phoneNumber;
        this.tableNumber = tableNumber;
        this.numberOfChairs = numberOfChairs;
        this.status = status;
        this.numberOfTables = numberOfTables;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(Integer numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(Integer numberOfTables) {
        this.numberOfTables = numberOfTables;
    }
}
