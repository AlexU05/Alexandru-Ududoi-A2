package model.value;

import model.type.Type;

public record StringValue(String value) implements Value {
    @Override
    public String toString() {
        return value;
    }

    @Override
    public Type getType() {
        return Type.STRING;
    }

    @Override
    public Value deepCopy() {
        return new StringValue(value);
    }

    @Override
    public BooleanValue equals(Value other) {
        if (other instanceof StringValue stringValue) {
            return new BooleanValue(this.value.equals(stringValue.value));
        }
        return new BooleanValue(false);
    }


}
