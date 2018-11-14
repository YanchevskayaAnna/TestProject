package dao.SQLDao;

import dao.interfaces.ITrainDAO;
import model.Carriage;
import model.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLTrainDAO extends SQLDao<Train,Integer> implements ITrainDAO {

    public SQLTrainDAO(Connection connection) {
        super(Train.class, Integer.class, connection);
    }

    @Override
    public List<Train> getAll() {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM trains;");) {
            List<Train> trainList = getAllTrainsFromResultSet(resultSet);
            for (Train train:trainList) {
                train.setLuggageWeight(CalculateLuggageWeight(train.getId()));
                train.setCountOfPassengers(CalculateCountPassengers(train.getId()));
            }
            return trainList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Train entity) {

        String sqlQuery = "UPDATE trains SET train_name=?, train_number=? WHERE id=?";

        try {
            if (getById(entity.getId()) == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.setInt(3, entity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean create(Train entity) {
        String sqlQuery = "INSERT INTO trains (train_name, train_number) VALUES (?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Train entity) {
        String sqlQuery = "DELETE FROM trains WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Train> getAllTrainsFromResultSet(ResultSet resultSet) throws SQLException {

        List<Train> trainList = new ArrayList<>();
        while (resultSet.next()) {
            Train train = new Train();
            train.setId(resultSet.getInt("id"));
            train.setName(resultSet.getString("train_name"));
            train.setNumber(resultSet.getString("train_number"));
            trainList.add(train);
        }
        return trainList;
    }


    @Override
    public int CalculateLuggageWeight(int trainID) {

        String query = "SELECT SUM(carriages.carriage_luggageweight) as luggageWeight  " +
                " FROM carriages" +
                " WHERE carriages.id_train = ?;";

        try (PreparedStatement preparedStatementSelect = connection.prepareStatement(query);) {
            preparedStatementSelect.setInt(1, trainID);
            ResultSet resultSet = preparedStatementSelect.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("luggageWeight");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return 0;
    }

    @Override
    public int CalculateCountPassengers(int trainID) {
        String query = "SELECT SUM(carriages.carriage_countofpassengers) as countofpassengers  " +
                " FROM carriages" +
                " WHERE carriages.id_train = ?;";

        try (PreparedStatement preparedStatementSelect = connection.prepareStatement(query);) {
            preparedStatementSelect.setInt(1, trainID);
            ResultSet resultSet = preparedStatementSelect.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("countofpassengers");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return 0;
    }
}
