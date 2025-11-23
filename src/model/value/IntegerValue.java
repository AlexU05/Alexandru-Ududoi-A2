package model.value;

import model.type.SimpleType;
import model.type.Type;

public record IntegerValue(int value) implements Value {

    @Override
    public Type getType() {
        return SimpleType.INTEGER;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value deepCopy() {
        return new IntegerValue(value);
    }

    @Override
    public BooleanValue equals(Value other) {
        if (other instanceof IntegerValue) {
            return new BooleanValue(this.value == ((IntegerValue) other).value);
        }
        return new  BooleanValue(false);
    }
}
