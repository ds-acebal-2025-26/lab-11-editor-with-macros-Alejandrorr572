package es.uniovi.eii.ds.commands;

public class DeleteCommand implements Command {

    @Override
    public StringBuilder execute(StringBuilder text) {
        int indexOfLastWord = text.toString().trim().lastIndexOf(" ");
        if (indexOfLastWord == -1)
            text.setLength(0);
        else
            text.setLength(indexOfLastWord);
        return text;
    }
    
}
