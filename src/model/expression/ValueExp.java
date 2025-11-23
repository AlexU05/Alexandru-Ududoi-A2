package model.expression;

import model.value.Value;
import state.SymbolTable;
import state.Heap;

public record ValueExp(Value value) implements Expression{
    @Override
    public Value evaluate(SymbolTable symbolTable) {
        return value;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        return value;
    }

    @Override
    public Expression deepCopy() {
        return new ValueExp(value);
    }
}
