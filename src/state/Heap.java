package state;

import model.value.Value;
import java.util.Map;

public interface Heap {
    int allocate(Value value);
    boolean contains(int address);
    Map<Integer, Value> getContent();
    void setContent(Map<Integer, Value> content);
    Value get(int address);
    void update(int address, Value value);
}