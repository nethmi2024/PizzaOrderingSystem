package com.pizza.command;
import java.util.*;

public class CommandExecutor {
    private List<Command> history = new ArrayList<>();
    public void executeCommand(Command cmd) {
        history.add(cmd);
        cmd.execute();
    }
}
