package es.uniovi.eii.ds.commands;

import java.util.ArrayList;

public class MacroCommand implements Command {
    private ArrayList<Command> commands;
    
    public MacroCommand() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
    
    @Override
    public StringBuilder execute(StringBuilder text) {
        for (Command command : commands) {
            text = command.execute(text);
        }
        return text;
    }
    
}
