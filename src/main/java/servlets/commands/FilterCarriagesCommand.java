package servlets.commands;

import servlets.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;


public class FilterCarriagesCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        int countPassengerFrom = Integer.parseInt(request.getParameter("CountPassengerFrom"));
        int countPassengerTo = Integer.parseInt(request.getParameter("CountPassengerTo"));
        request.setAttribute("carriages", carriageService.getCarriagesFilterCountPassengers(carriageService.getAllCarriages(), countPassengerFrom, countPassengerTo));
        forward("carriages");
    }
}
