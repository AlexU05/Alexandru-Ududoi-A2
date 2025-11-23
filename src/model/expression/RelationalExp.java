package model.expression;

import model.exception.InvalidOperatorException;
import model.exception.InvalidTypeException;
import model.value.BooleanValue;
import model.value.IntegerValue;
import model.value.Value;
import state.SymbolTable;
import state.Heap;

public record RelationalExp(Expression left, Expression right, String operator) implements Expression {

    @Override
    public Value evaluate(SymbolTable symbolTable) throws InvalidTypeException, InvalidOperatorException {
        Value leftValue = left.evaluate(symbolTable);
        Value rightValue = right.evaluate(symbolTable);
        if (!(leftValue instanceof IntegerValue) || !(rightValue instanceof IntegerValue))
            throw new InvalidTypeException("RelationalExp requires integer values");
        return switch (operator) {
            case "<" -> new BooleanValue(((IntegerValue) leftValue).value() < ((IntegerValue) rightValue).value());
            case "<=" -> new BooleanValue(((IntegerValue) leftValue).value() <= ((IntegerValue) rightValue).value());
            case "==" -> leftValue.equals(rightValue);
            case "!=" -> new BooleanValue(!leftValue.equals(rightValue).value());
            case ">" -> new BooleanValue(((IntegerValue) leftValue).value() > ((IntegerValue) rightValue).value());
            case ">=" -> new BooleanValue(((IntegerValue) leftValue).value() >= ((IntegerValue) rightValue).value());
            default -> throw new InvalidOperatorException(operator);
        };
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) throws InvalidTypeException, InvalidOperatorException {
        Value leftValue = left.evaluate(symbolTable, heap);
        Value rightValue = right.evaluate(symbolTable, heap);
        if (!(leftValue instanceof IntegerValue) || !(rightValue instanceof IntegerValue))
            throw new InvalidTypeException("RelationalExp requires integer values");
        return switch (operator) {
            case "<" -> new BooleanValue(((IntegerValue) leftValue).value() < ((IntegerValue) rightValue).value());
            case "<=" -> new BooleanValue(((IntegerValue) leftValue).value() <= ((IntegerValue) rightValue).value());
            case "==" -> leftValue.equals(rightValue);
            case "!=" -> new BooleanValue(!leftValue.equals(rightValue).value());
            case ">" -> new BooleanValue(((IntegerValue) leftValue).value() > ((IntegerValue) rightValue).value());
            case ">=" -> new BooleanValue(((IntegerValue) leftValue).value() >= ((IntegerValue) rightValue).value());
            default -> throw new InvalidOperatorException(operator);
        };
    }

    @Override
    public Expression deepCopy() {
        return new RelationalExp(left.deepCopy(), right.deepCopy(), operator);
    }
}
