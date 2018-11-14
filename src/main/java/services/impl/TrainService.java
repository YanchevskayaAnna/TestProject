package services.impl;

import dao.interfaces.ITrainDAO;
import model.Carriage;
import model.Train;
import services.interfaces.ITrainService;

import java.util.List;

public class TrainService implements ITrainService{
    private ITrainDAO trainDAO;

    public TrainService(ITrainDAO trainDAO) {
        this.trainDAO = trainDAO;
    }

    @Override
    public List<Train> getAllTrains() {
        return trainDAO.getAll();
    }

    @Override
    public Train getTrainId(Integer id) {
        return trainDAO.getById(id);
    }

    @Override
    public boolean updateTrain(Train train) {
        return trainDAO.update(train);
    }

    @Override
    public boolean createTrain(Train train) {
        return trainDAO.create(train);
    }

    @Override
    public boolean deleteTrain(Train train) {
        return trainDAO.delete(train);
    }

    @Override
    public boolean deleteTrainById(Integer id) {
        return trainDAO.deleteById(id);
    }



}
