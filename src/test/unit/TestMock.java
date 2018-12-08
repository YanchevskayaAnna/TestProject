package unit;

import dao.SQLDao.SQLTrainDAO;
import model.Train;
import org.junit.BeforeClass;
import org.junit.Test;
import services.impl.TrainService;
import services.interfaces.ITrainService;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestMock {

    private static ITrainService trainService;

    @BeforeClass
    public static void init() {

        Train train1 = new Train("Киев-Харьков", "123");
        Train train2 = new Train("Киев-Херсон", "124");
        Train train3 = new Train("Киев-Хмельницкий", "125");

        SQLTrainDAO trainDao = mock(SQLTrainDAO.class);
        when(trainDao.getAll()).thenReturn(
                Arrays.asList(
                        train1, train2, train3));

        trainService = new TrainService(trainDao);
    }

    @Test
    public void getAllTrains() {
        assertNotNull(trainService.getAllTrains());
        assertTrue(trainService.getAllTrains().size() == 3);
    }

}
