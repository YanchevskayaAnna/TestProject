package servlets;

import services.interfaces.ICarriageService;
import services.interfaces.ITrainService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public abstract class FrontCommand {
    private ServletContext context;
    protected HttpServletRequest request;
    private HttpServletResponse response;
    protected ICarriageService carriageService;
    protected ITrainService trainService;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            ICarriageService carriageService,
            ITrainService trainService) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.carriageService = carriageService;
        this.trainService = trainService;

    }

    public abstract void process() throws ServletException, IOException;

    protected void forward(String target) throws ServletException, IOException {
        target = String.format("/WEB-INF/pages/%s.jsp", target);
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}