package model;

import anotations.Column;
import anotations.Table;

@Table(name="carriages")
public class Carriage extends _IDEntity{

    @Column(name = "carriage_number")
    private int number;
    @Column(name = "carriage_comfortlevel")
    private int comfortLevel;
    @Column(name = "carriage_countofpassengers")
    private int countOfPassengers;
    @Column(name = "carriage_luggageweight")
    private int luggageWeight;
    @Column(name = "carriage_typeofcarriage")
    private TypeOfCarriage typeOfCarriage;
    @Column(name = "id_train")
    private int idTrain;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(int comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    public int getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(int luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public TypeOfCarriage getTypeOfCarriage() {
        return typeOfCarriage;
    }

    public void setTypeOfCarriage(TypeOfCarriage typeOfCarriage) {
        this.typeOfCarriage = typeOfCarriage;
    }

    public int getIdTrain() {
        return idTrain;
    }

    public void setIdTrain(int idTrain) {
        this.idTrain = idTrain;
    }
}
