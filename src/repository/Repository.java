package repository;

import model.statement.Statement;
import state.ProgramState;

import java.io.IOException;

public interface Repository {
    void setProgramState(ProgramState state);
    ProgramState getProgramState();
    void logProgramState() throws IOException;
}
