package model.value;

import model.type.Type;

public record BooleanValue(boolean value) implements Value {

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Value deepCopy() {
        return new BooleanValue(value);
    }

    @Override
    public BooleanValue equals(Value other) {
        if (other instanceof BooleanValue otherBool) {
            return new BooleanValue(this.value == otherBool.value);
        }
        return new BooleanValue(false);
    }
}
