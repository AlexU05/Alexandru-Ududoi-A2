package repository;


import model.statement.Statement;
import state.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RepositoryImp1 implements  Repository {
    private final List<ProgramState> programStates;
    private final String logFilePath;
    public RepositoryImp1(ProgramState programState) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        logFilePath = "logFile.txt";
    }

    public RepositoryImp1(ProgramState programState, String logFilePath) {
        this.programStates = new ArrayList<>();
        this.programStates.add(programState);
        this.logFilePath = logFilePath;
    }

    @Override
    public void setProgramState(ProgramState state) {
        this.programStates.add(state);
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
        printWriter.println(programStates.get(0).toString());
        printWriter.close();
    }
}
