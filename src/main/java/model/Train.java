package model;

import anotations.Column;
import anotations.Table;

import java.util.List;
import java.util.Objects;

@Table(name="trains")
public class Train extends _IDEntity{

    @Column(name = "train_name")
    private String name;

    @Column(name = "train_number")
    private String number;

    private int luggageWeight;

    private int countOfPassengers;

//    List<Carriage> carriageList; //to do связи в SQL

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(int luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    public int getCountOfPassengers() {
        return countOfPassengers;
    }

    public void setCountOfPassengers(int countOfPassengers) {
        this.countOfPassengers = countOfPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(name, train.name) &&
                Objects.equals(number, train.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, number);
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", luggageWeight=" + luggageWeight +
                ", countOfPassengers=" + countOfPassengers +
                '}';
    }
}
