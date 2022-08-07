package com.bsu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bag<T extends Item> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item){
        items.add(item);
    }

    public void removeItem(T item){
        items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public <E extends Item> int compareTo(Bag<E> otherBag){
        return items.stream().mapToInt(Item::getWeight).sum() - otherBag.items.stream().mapToInt(Item::getWeight).sum();
    }
}
