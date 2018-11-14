package dao.SQLDao;

import dao.interfaces.DaoFactory;
import dao.interfaces.ICarriageDAO;
import dao.interfaces.ITrainDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLDaoFactory extends DaoFactory {

    private static final String PROPERTIES_PATH = "src/main/resources/properties";

    @Override
    public ICarriageDAO createCarriageDao() {
        return new SQLCarriageDAO(getConnection());
    }

    @Override
    public ITrainDAO createTrainDao() {
        return new SQLTrainDAO(getConnection());
    }

    private Connection getConnection(){
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(PROPERTIES_PATH)));
            return DriverManager.getConnection(
                    properties.getProperty("URL"),
                    properties.getProperty("USER"),
                    properties.getProperty("PASSWORD"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
