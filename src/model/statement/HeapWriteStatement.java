package model.statement;

import model.exception.InvalidTypeException;
import model.exception.RuntimeInterpreterException;
import model.exception.VariableNotDefinedException;
import model.expression.Expression;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.Value;
import state.ProgramState;
import state.SymbolTable;
import state.Heap;

public record HeapWriteStatement(String variableName, Expression expression) implements Statement {

    @Override
    public ProgramState execute(ProgramState state) throws VariableNotDefinedException, InvalidTypeException, RuntimeInterpreterException {
        SymbolTable symbolTable = state.symbolTable();
        Heap heap = state.heap();

        // Check if variable is defined
        if (!symbolTable.isDefined(variableName)) {
            throw new VariableNotDefinedException(variableName);
        }

        // Check if variable type is a ReferenceType
        var variableType = symbolTable.getVariableType(variableName);
        if (!(variableType instanceof ReferenceType refType)) {
            throw new InvalidTypeException("Variable '" + variableName + "' is not of reference type");
        }

        // Get the reference value from the symbol table
        Value varValue = symbolTable.getValue(variableName);
        if (!(varValue instanceof ReferenceValue refValue)) {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' does not contain a valid reference value");
        }

        // Get the address
        int address = refValue.getAddr();

        // Check if the address is in the heap
        if (!heap.contains(address)) {
            throw new RuntimeInterpreterException("Address " + address + " is not in the heap");
        }

        // Evaluate the expression
        Value evaluatedValue = expression.evaluate(symbolTable, heap);

        // Get the location type from the ReferenceType
        var locationType = refType.getInner();

        // Check if the evaluated value's type matches the location type
        if (!evaluatedValue.getType().equals(locationType)) {
            throw new InvalidTypeException("Expression type does not match the reference's location type. " +
                    "Expected: " + locationType + ", Got: " + evaluatedValue.getType());
        }

        // Update the heap with the new value
        heap.update(address, evaluatedValue);

        return state;
    }

    @Override
    public Statement deepCopy() {
        return new HeapWriteStatement(variableName, expression.deepCopy());
    }
}

