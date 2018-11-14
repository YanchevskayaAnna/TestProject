package services.impl;

import dao.interfaces.ICarriageDAO;
import model.Carriage;
import services.interfaces.ICarriageService;

import java.util.List;
import java.util.stream.Collectors;

public class CarriageService implements ICarriageService{
    private ICarriageDAO carriageDAO;

    public CarriageService(ICarriageDAO carriageDAO) {
        this.carriageDAO = carriageDAO;
    }

    @Override
    public List<Carriage> getAllCarriages() {
        return carriageDAO.getAll();
    }

    @Override
    public List<Carriage> getAllCarriagesTrain(int trainID) {
        return carriageDAO.getAllCarriagesTrain(trainID);
    }

    @Override
    public List<Carriage> getCarriagesFilterCountPassengersTrain(int trainID, int countPassengersFrom, int countPassengersTo) {
        return carriageDAO.getCarriagesFilterCountPassengersTrain(trainID, countPassengersFrom, countPassengersTo);
    }

    @Override
    public Carriage getCarriageId(Integer id) {
        return carriageDAO.getById(id);
    }

    @Override
    public boolean updateCarriage(Carriage carriage) {
        return carriageDAO.update(carriage);
    }

    @Override
    public boolean createCarriage(Carriage carriage) {
        return carriageDAO.create(carriage);
    }

    @Override
    public boolean deleteCarriage(Carriage carriage) {
        return carriageDAO.delete(carriage);
    }

    @Override
    public boolean deleteCarriageById(Integer id) {
        return carriageDAO.deleteById(id);
    }

    @Override
    public List<Carriage> sortCarriagesComfortLevel(List<Carriage> carriages) {
        carriages.sort((o1, o2) -> o1.getComfortLevel() - o2.getComfortLevel());
        return carriages;
    }

    @Override
    public List<Carriage> sortCarriagesNumber(List<Carriage> carriages) {
        carriages.sort((o1, o2) -> o1.getNumber() - o2.getNumber());
        return carriages;
    }

    @Override
    public List<Carriage> getCarriagesFilterCountPassengers(List<Carriage> carriages, int countPassengersFrom, int countPassengersTo) {
        //return carriageDAO.getCarriagesFilterCountPassengers(countPassengersFrom, countPassengersTo);
        return
                carriages.stream()
                        .filter(i -> i.getCountOfPassengers() >= countPassengersFrom && i.getCountOfPassengers() <= countPassengersTo)
                        .collect(Collectors.toList());
    }
}
