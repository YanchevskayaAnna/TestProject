package servlets.commands;

import servlets.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class SortNumberCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("carriages", carriageService.sortCarriagesNumber(carriageService.getAllCarriages()));
        forward("carriages");
    }
}
