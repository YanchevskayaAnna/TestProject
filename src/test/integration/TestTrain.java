package integration;

import dao.interfaces.DaoFactory;
import model.Train;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import services.impl.TrainService;
import services.interfaces.ITrainService;


public class TestTrain {

    private static ITrainService trainService;

    @BeforeClass
    public static void beforeClass()  {
        DaoFactory factory = DaoFactory.getInstance();
        trainService = new TrainService(factory.createTrainDao());
    }

    @AfterClass
    public static void afterClass()  {

    }

    @Test
    public void testCreateTrain() {
        int countTrainsBefore = trainService.getAllTrains().size();
        Train train = new Train("Киев-Херсон", "123");
        trainService.createTrain(train);
        int countTrainsAfter = trainService.getAllTrains().size();
        Assert.assertNotNull(countTrainsAfter = countTrainsBefore + 1);
    }

}
