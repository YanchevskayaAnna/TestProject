package services.interfaces;

import model.Carriage;
import model.Train;

import java.util.List;

public interface ICarriageService {
    List<Carriage> getAllCarriages();
    List<Carriage> getAllCarriagesTrain(int trainID);
    List<Carriage> getCarriagesFilterCountPassengersTrain(int trainID, int countPassengersFrom, int countPassengersTo);
    Carriage getCarriageId(Integer id);
    boolean updateCarriage(Carriage carriage);
    boolean createCarriage(Carriage carriage);
    boolean deleteCarriage(Carriage carriage);
    boolean deleteCarriageById(Integer id);
    List<Carriage> sortCarriagesComfortLevel(List<Carriage> carriages);
    List<Carriage> sortCarriagesNumber(List<Carriage> carriages);
    List<Carriage> getCarriagesFilterCountPassengers(List<Carriage> carriages, int countPassengersFrom, int countPassengersTo); //to do
}
