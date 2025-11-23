package model.expression;

import model.value.Value;
import state.SymbolTable;
import state.Heap;

public interface Expression {
    Value evaluate(SymbolTable symbolTable);
    Value evaluate(SymbolTable symbolTable, Heap heap);
    Expression deepCopy();
}
