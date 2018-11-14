package dao.interfaces;

import model.Carriage;
import model.Train;

import java.util.List;

public interface ITrainDAO extends IAbstractDAO<Train> {

    int CalculateLuggageWeight(int trainID);
    int CalculateCountPassengers(int trainID);

}
