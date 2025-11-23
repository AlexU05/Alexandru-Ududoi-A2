package repository;


import state.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryInterface {
    private final List<ProgramState> programStates;
    private final String logFilePath;
    public Repository(ProgramState programState) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        logFilePath = "logFile.txt";
    }

    public Repository(ProgramState programState, String logFilePath) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        this.logFilePath = logFilePath;
    }

    @Override
    public void setProgramState(ProgramState state) {
        this.programStates.set(0, state);
    }

    @Override
    public ProgramState getProgramState() {
        return programStates.get(0);
    }

    @Override
    public void logProgramState() throws IOException {
        PrintWriter printWriter = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(logFilePath, true)));
        printWriter.println(getProgramState().toString());
        printWriter.close();
    }
}
