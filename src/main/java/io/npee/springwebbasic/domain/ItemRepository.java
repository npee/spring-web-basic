package io.npee.springwebbasic.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParams) {
        Item item = findById(itemId);
        item.setItemName(updateParams.getItemName());
        item.setPrice(updateParams.getPrice());
        item.setQuantity(updateParams.getQuantity());
        item.setOpen(updateParams.getOpen());
        item.setRegions(updateParams.getRegions());
        item.setItemName(updateParams.getItemName());
        item.setDeliveryCode(updateParams.getDeliveryCode());
    }

    public void clearStore() {
        store.clear();
    }
}
