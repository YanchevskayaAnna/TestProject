package servlets;

import dao.SQLDao.SQLCarriageDAO;
import dao.SQLDao.SQLTrainDAO;
import dao.interfaces.DaoFactory;
import services.impl.CarriageService;
import services.impl.TrainService;
import services.interfaces.ICarriageService;
import services.interfaces.ITrainService;
import servlets.commands.UnknownCommand;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//https://www.baeldung.com/java-front-controller-pattern

@WebServlet(urlPatterns = {"/"})
public class FrontControllerServlet extends HttpServlet {

    private ICarriageService carriageService;
    private ITrainService trainService;

    @Override
    public void init() throws ServletException {
        DaoFactory factory = DaoFactory.getInstance();
        trainService = new TrainService(factory.createTrainDao());
        carriageService = new CarriageService(factory.createCarriageDao());
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response, carriageService, trainService);
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(
                    "servlets.commands.%sCommand",
                    request.getParameter("command") == null ? "ShowTrains" :request.getParameter("command")));
            return (FrontCommand) type
                    .asSubclass(FrontCommand.class)
                    .newInstance();
        } catch (Exception e) {
            return new UnknownCommand();
        }
    }
}