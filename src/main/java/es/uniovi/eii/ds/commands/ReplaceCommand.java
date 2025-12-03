package es.uniovi.eii.ds.commands;

public class ReplaceCommand implements Command {
    private final String[] args;
    
    public ReplaceCommand(String[] args) {
        this.args = args;
    }
    
    @Override
    public StringBuilder execute(StringBuilder text) {
		String find = args[0];
		String replace = args[1];
		text = new StringBuilder(text.toString().replace(find, replace));
        return text;
    }
}
