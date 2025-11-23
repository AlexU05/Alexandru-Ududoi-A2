package repository;

import state.ProgramState;

import java.io.IOException;

public interface RepositoryInterface {
    void setProgramState(ProgramState state);
    ProgramState getProgramState();
    void logProgramState() throws IOException;
}
