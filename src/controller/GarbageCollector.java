package controller;

import model.value.ReferenceValue;
import model.value.Value;

import java.util.*;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static Set<Integer> getReachableAddresses(Collection<Value> symTableValues, Map<Integer, Value> heap) {
        Set<Integer> reachable = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (Value v : symTableValues) {
            if (v instanceof ReferenceValue ref) {
                int addr = ref.getAddr();
                if (!reachable.contains(addr)) {
                    reachable.add(addr);
                    stack.push(addr);
                }
            }
        }

        while (!stack.isEmpty()) {
            int addr = stack.pop();
            Value heapVal = heap.get(addr);
            if (heapVal instanceof ReferenceValue ref) {
                int innerAddr = ref.getAddr();
                if (!reachable.contains(innerAddr)) {
                    reachable.add(innerAddr);
                    stack.push(innerAddr);
                }
            }
        }

        return reachable;
    }

    public static Map<Integer, Value> safeGarbageCollector(Collection<Value> symTableValues, Map<Integer, Value> heap) {
        Set<Integer> reachable = getReachableAddresses(symTableValues, heap);
        return heap.entrySet().stream()
                .filter(e -> reachable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
