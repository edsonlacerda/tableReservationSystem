package com.edsonlacerda.table;

public class TablesDTO {
    private Integer numberOfChairs;
    private Integer numberOfTables;

    public TablesDTO() {
    }

    public TablesDTO(Integer numberOfChairs, Integer numberOfTables) {
        this.numberOfChairs = numberOfChairs;
        this.numberOfTables = numberOfTables;
    }

    public Integer getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(Integer numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }

    public Integer getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(Integer numberOfTables) {
        this.numberOfTables = numberOfTables;
    }
}
