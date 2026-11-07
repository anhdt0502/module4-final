package com.fashion.session;

import java.util.HashMap;
import java.util.Map;

public class CartSession {

    private Map<Long, Integer> items = new HashMap<>();

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void addProduct(Long productId) {
        items.put(
                productId,
                items.getOrDefault(productId, 0) + 1
        );
    }

    public void removeProduct(Long productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }
}