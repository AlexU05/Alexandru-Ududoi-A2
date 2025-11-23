package model.value;

import model.type.ReferenceType;
import model.type.Type;

public class ReferenceValue implements Value{
    int address;
    Type locationType;
    public ReferenceValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddr() {
        return address;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public Value deepCopy() {
        return new ReferenceValue(address, locationType);
    }

    @Override
    public BooleanValue equals(Value other) {
        if (other instanceof ReferenceValue otherRef) {
            return new BooleanValue(this.address == otherRef.address && this.locationType.equals(otherRef.locationType));
        }
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "(" + address + "," + locationType + ")";
    }
}
