package model.expression;

import model.exception.VariableNotDefinedException;
import model.value.Value;
import state.SymbolTable;
import state.Heap;

public record VarExp(String variableName) implements Expression {
    @Override
    public Value evaluate(SymbolTable symbolTable) throws  VariableNotDefinedException {
        if(!symbolTable.isDefined(variableName)){
            throw new VariableNotDefinedException(variableName);
        }
        return symbolTable.getValue(variableName);
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) throws VariableNotDefinedException {
        return evaluate(symbolTable);
    }

    @Override
    public Expression deepCopy() {
        return new VarExp(variableName);
    }
}
