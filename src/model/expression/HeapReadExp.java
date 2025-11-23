package model.expression;

import model.exception.InvalidTypeException;
import model.exception.RuntimeInterpreterException;
import model.value.ReferenceValue;
import model.value.Value;
import state.SymbolTable;
import state.Heap;

public record HeapReadExp(Expression expression) implements Expression {

    @Override
    public Value evaluate(SymbolTable symbolTable) {
        throw new RuntimeInterpreterException("HeapReadExp requires heap context. Use evaluate(SymbolTable, Heap) instead.");
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) throws InvalidTypeException, RuntimeInterpreterException {
        // Evaluate the expression
        Value evaluatedValue = expression.evaluate(symbolTable, heap);

        // Check if the result is a ReferenceValue
        if (!(evaluatedValue instanceof ReferenceValue refValue)) {
            throw new InvalidTypeException("HeapReadExp: expression must evaluate to a reference value, got " + evaluatedValue.getType());
        }

        // Get the address from the ReferenceValue
        int address = refValue.getAddr();

        // Check if the address is in the heap
        if (!heap.contains(address)) {
            throw new RuntimeInterpreterException("HeapReadExp: address " + address + " is not in the heap");
        }

        // Return the value at that address
        return heap.get(address);
    }

    @Override
    public Expression deepCopy() {
        return new HeapReadExp(expression.deepCopy());
    }
}

