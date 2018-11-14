package services.interfaces;

import model.Carriage;
import model.Train;

import java.util.List;

public interface ITrainService {
    List<Train> getAllTrains();
    Train getTrainId(Integer id);
    boolean updateTrain(Train train);
    boolean createTrain(Train train);
    boolean deleteTrain(Train train);
    boolean deleteTrainById(Integer id);
}
