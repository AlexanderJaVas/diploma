package com.example.coursework.services;

import com.example.coursework.models.Category;
import com.example.coursework.models.Item;
import com.example.coursework.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final CartService cartService;

    public ItemService(ItemRepository itemRepository, OrderService orderService, CartService cartService) {
        this.itemRepository = itemRepository;
        this.orderService = orderService;
        this.cartService = cartService;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(int id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.orElse(null);
    }

    @Transactional
    public void saveItem(Item item, Category category) {
        item.setCategory(category);
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(int id, Item item) {
        item.setId(id);
        item.setDateTime(LocalDateTime.now());
        itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    public boolean isItemUsed(int id) {
        boolean isItemInOrders = orderService.itemByIdInOrders(id);
        boolean isItemInCarts = cartService.itemByIdInCarts(id);
        return isItemInOrders || isItemInCarts;
    }

    public List<Item> getByTitleContainingIgnoreCase(String title) {
        return itemRepository.findByTitleContainingIgnoreCase(title);
    }

    ;

    public List<Item> getByTitleAndPriceGreaterThanEqualAndPriceLessThenEqual(String title, float from, float to) {
        return itemRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(title, from, to);
    }

    ;

    public List<Item> getByTitleOrderByPriceAsc(String title, float from, float to) {
        return itemRepository.findByTitleOrderByPriceAsc(title, from, to);
    }

    ;

    public List<Item> getByTitleOrderByPriceDesc(String title, float from, float to) {
        return itemRepository.findByTitleOrderByPriceDesc(title, from, to);
    }

    ;

    public List<Item> getByTitleAndCategoryOrderByPriceAsc(String title, float from, float to, int category) {
        return itemRepository.findByTitleAndCategoryOrderByPriceAsc(title, from, to, category);
    }

    ;

    public List<Item> getByTitleAndCategoryOrderByPriceDesc(String title, float from, float to, int category) {
        return itemRepository.findByTitleAndCategoryOrderByPriceDesc(title, from, to, category);
    }

    ;
}
