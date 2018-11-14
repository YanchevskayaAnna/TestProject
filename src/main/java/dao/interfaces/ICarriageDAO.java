package dao.interfaces;

import model.Carriage;
import model.Train;

import java.util.List;

public interface ICarriageDAO extends IAbstractDAO<Carriage> {
    List<Carriage> getAllCarriagesTrain(int trainID);
    List<Carriage> getCarriagesFilterCountPassengersTrain(int trainID, int countPassengersFrom, int countPassengersTo);
    List<Carriage> getCarriagesFilterCountPassengers(int countPassengersFrom, int countPassengersTo);
}
