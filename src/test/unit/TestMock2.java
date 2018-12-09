package unit;

import dao.SQLDao.SQLTrainDAO;
import model.Train;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import services.impl.TrainService;
import services.interfaces.ITrainService;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class TestMock2 {
    @Mock
    SQLTrainDAO trainDao;
    private static ITrainService trainService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this); //analog @RunWith(MockitoJUnitRunner.class)
        Train train1 = new Train("Киев-Харьков", "123");
        Train train2 = new Train("Киев-Херсон", "124");
        Train train3 = new Train("Киев-Хмельницкий", "125");

        when(trainDao.getAll()).thenReturn(
                Arrays.asList(
                        train1, train2, train3));

       trainService = new TrainService(trainDao);
    }

    @Test
    public void getAllTrains() {
        assertNotNull(trainService.getAllTrains());
        assertTrue(trainService.getAllTrains().size() == 3);
        verify(trainDao, times(2)).getAll();
    }
}
