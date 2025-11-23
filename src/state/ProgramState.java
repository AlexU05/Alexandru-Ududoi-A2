package state;

import model.statement.Statement;

public record ProgramState(
        ExecutionStack executionStack,
        SymbolTable symbolTable,
        Out out,
        FileTable fileTable,
        Heap heap) {

    @Override
    public String toString() {
        return "\nProgramState: " +
                "\nexecutionStack: " + executionStack + ", " +
                "\nsymbolTable: " + symbolTable + ", " +
                "\nout: " + out +
                "\nfileTable: " + fileTable +
                "\nheap: " + heap + "\n";

    }
}
