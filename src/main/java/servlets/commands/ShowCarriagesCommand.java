package servlets.commands;

import servlets.FrontCommand;
import javax.servlet.ServletException;
import java.io.IOException;


public class ShowCarriagesCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("trainNumber", request.getParameter("trainNumber"));
        request.setAttribute("carriages", carriageService.getAllCarriagesTrain(Integer.parseInt(request.getParameter("trainID"))));
        forward("carriages");
    }
}
