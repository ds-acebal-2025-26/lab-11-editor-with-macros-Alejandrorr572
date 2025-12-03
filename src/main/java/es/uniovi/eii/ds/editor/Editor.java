package es.uniovi.eii.ds.editor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import es.uniovi.eii.ds.commands.Command;
import es.uniovi.eii.ds.commands.MacroCommand;

public class Editor {

    private StringBuilder text = new StringBuilder();

    private Map<String, MacroCommand> macros = new HashMap<>();
    private MacroCommand currentRecording = null;
    private String currentMacroName = null;

    public void open(String filename) {
        try {
            text = new StringBuilder(readFile(filename));
        } catch (Exception e) {
            System.out.println("Document could not be opened");
        }
    }

    public void apply(Command command) {
        text = command.execute(text);
        if (currentRecording != null) {
            currentRecording.addCommand(command);
        }
    }

    public void startRecording(String macroName) {
        currentMacroName = macroName;
        currentRecording = new MacroCommand();
        System.out.println("Recording macro: " + currentMacroName);
    }

    public void stopRecording() {
        if (currentRecording != null) {
            macros.put(currentMacroName, currentRecording);
            System.out.println("Macro saved: " + currentMacroName);
            currentRecording = null;
            currentMacroName = null;
        }
    }

    public Command getMacro(String name) {
        return macros.get(name);
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public StringBuilder getText() {
        return text;
    }

    private String readFile(String filename) {
        InputStream in = getClass().getResourceAsStream("/" + filename);
        if (in == null)
            throw new IllegalArgumentException("File not found: " + filename);

        try (BufferedReader input = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder result = new StringBuilder();
            String line;
            boolean firstLine = true;
            while ((line = input.readLine()) != null) {
                if (!firstLine)
                    result.append(System.lineSeparator());
                result.append(line);
                firstLine = false;
            }
            return result.toString();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
