package es.uniovi.eii.ds.commands;

public class InsertCommand implements Command {
    private final String[] words;
    
    public InsertCommand(String[] words) {
        this.words = words;
    }
    
    @Override
    public StringBuilder execute(StringBuilder text) {
        for (String word : words) {
            text.append(" ").append(word);
        }
        return text;
    }
}