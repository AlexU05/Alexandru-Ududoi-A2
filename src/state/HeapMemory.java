package state;

import model.value.Value;
import java.util.HashMap;
import java.util.Map;

public class HeapMemory implements Heap {
    private final Map<Integer, Value> heap;
    private int freeLocation;

    public HeapMemory() {
        this.heap = new HashMap<>();
        this.freeLocation = 1;
    }

    @Override
    public int allocate(Value value) {
        if (value == null) {
            throw new RuntimeException("Cannot allocate null value in heap");
        }
        int addr = freeLocation;
        heap.put(addr, value);
        advanceFreeLocation();
        return addr;
    }

    @Override
    public boolean contains(int address) {
        return heap.containsKey(address);
    }

    @Override
    public Map<Integer, Value> getContent() {
        return new HashMap<>(heap);
    }

    @Override
    public void setContent(Map<Integer, Value> content) {
        heap.clear();
        if (content != null) {
            heap.putAll(content);
        }
        freeLocation = 1;
        advanceFreeLocation();
    }

    private void advanceFreeLocation() {
        while (heap.containsKey(freeLocation)) {
            freeLocation++;
        }
        if (freeLocation <= 0) {
            throw new RuntimeException("Heap address overflow");
        }
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    @Override
    public Value get(int address) {
        return heap.get(address);
    }

    @Override
    public void update(int address, Value value) {
        if (!heap.containsKey(address)) {
            throw new RuntimeException("Address " + address + " not found in heap");
        }
        heap.put(address, value);
    }
}
