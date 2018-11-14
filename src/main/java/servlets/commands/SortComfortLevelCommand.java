package servlets.commands;

import servlets.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

public class SortComfortLevelCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("carriages", carriageService.sortCarriagesComfortLevel(carriageService.getAllCarriages()));
        forward("carriages");
    }
}
