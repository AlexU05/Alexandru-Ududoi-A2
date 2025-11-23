package model.type;

import model.value.ReferenceValue;
import model.value.Value;

public class ReferenceType implements Type {
    Type inner;
    public ReferenceType(Type inner) {
        this.inner=inner;
    }
    public Type getInner() {
        return inner;
    }
    public boolean equals(Object another) {
        if (another instanceof ReferenceType otherRef) {
            return inner.equals(otherRef.getInner());
        }
        return false;
    }
    public String toString() { return "Ref(" +inner.toString()+")";}
    Value defaultValue() { return new ReferenceValue(0,inner);}

    @Override
    public Value getDefaultValue() {
        return new ReferenceValue(0, inner);
    }


}
