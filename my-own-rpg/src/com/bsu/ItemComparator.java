package com.bsu;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item e1, Item e2) {
        return Integer.compare(e1.getWeight(), e2.getWeight());
    }
}
