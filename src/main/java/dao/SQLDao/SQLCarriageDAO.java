package dao.SQLDao;

import dao.interfaces.ICarriageDAO;
import model.Carriage;
import model.Train;
import model.TypeOfCarriage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCarriageDAO extends SQLDao<Carriage,Integer> implements ICarriageDAO{

    public SQLCarriageDAO(Connection connection) {
        super(Carriage.class, Integer.class, connection);
    }

    @Override
    public List<Carriage> getAll() {

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM carriages;");) {
            List<Carriage> carriageList = getAllCarriagesFromResultSet(resultSet);
            return carriageList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean update(Carriage entity) {

        String sqlQuery = "UPDATE carriages SET carriage_number=?, carriage_comfortlevel=?, carriage_countofpassengers=? WHERE id=?";

        try {
            if (getById(entity.getId()) == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, entity.getNumber());
            preparedStatement.setInt(2, entity.getComfortLevel());
            preparedStatement.setInt(3, entity.getCountOfPassengers());
            preparedStatement.setInt(4, entity.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean create(Carriage entity) {
        String sqlQuery = "INSERT INTO carriages (carriage_number, carriage_comfortlevel, carriage_typeofcarriage, id_train) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, entity.getNumber());
            preparedStatement.setInt(2, entity.getComfortLevel());
            preparedStatement.setString(3, entity.getTypeOfCarriage().toString());
            preparedStatement.setInt(4, entity.getIdTrain());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Carriage entity) {
        String sqlQuery = "DELETE FROM carriages WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Carriage> getAllCarriagesFromResultSet(ResultSet resultSet) throws SQLException {

        List<Carriage> carriageList = new ArrayList<>();
        while (resultSet.next()) {
            Carriage carriage = new Carriage();
            carriage.setId(resultSet.getInt("id"));
            carriage.setNumber(resultSet.getInt("carriage_number"));
            carriage.setComfortLevel(resultSet.getInt("carriage_comfortlevel"));
            carriage.setCountOfPassengers(resultSet.getInt("carriage_countofpassengers"));
            carriage.setLuggageWeight(resultSet.getInt("carriage_luggageweight"));
            carriage.setTypeOfCarriage(TypeOfCarriage.valueOf(resultSet.getString("carriage_typeofcarriage")));
            carriage.setIdTrain(resultSet.getInt("id_train"));
            carriageList.add(carriage);
        }
        return carriageList;
    }


    @Override
    public List<Carriage> getAllCarriagesTrain(int trainID) {

        String sqlQuery = "SELECT * FROM carriages WHERE id_train = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, trainID);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Carriage> carriageList = getAllCarriagesFromResultSet(resultSet);
            return carriageList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Carriage> getCarriagesFilterCountPassengersTrain(int trainID, int countPassengersFrom, int countPassengersTo) {
        String sqlQuery = "SELECT * FROM carriages WHERE idTrain = ? and carriage_countofpassengers BETWEEN ? and ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, trainID);
            preparedStatement.setInt(2, countPassengersFrom);
            preparedStatement.setInt(3, countPassengersTo);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Carriage> carriageList = getAllCarriagesFromResultSet(resultSet);
            return carriageList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<Carriage> getCarriagesFilterCountPassengers(int countPassengersFrom, int countPassengersTo) {
        String sqlQuery = "SELECT * FROM carriages WHERE carriage_countofpassengers BETWEEN ? and ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){
            preparedStatement.setInt(1, countPassengersFrom);
            preparedStatement.setInt(2, countPassengersTo);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Carriage> carriageList = getAllCarriagesFromResultSet(resultSet);
            return carriageList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
