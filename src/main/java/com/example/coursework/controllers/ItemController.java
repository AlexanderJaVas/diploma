package com.example.coursework.controllers;

import com.example.coursework.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String getAllItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "/item/item";
    }

    @GetMapping("/info/{id}")
    public String infoItem(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "/item/info_item";
    }

    @PostMapping("/search")
    public String itemSearch(@RequestParam("search") String search,
                             @RequestParam("from") String from,
                             @RequestParam("to") String to,
                             @RequestParam(value = "price", required = false, defaultValue = "") String price,
                             @RequestParam(value = "contract", required = false, defaultValue = "") String contract,
                             Model model) {
        model.addAttribute("items", itemService.getAllItems());
        if (!from.isEmpty() & !to.isEmpty()) {
            if (!price.isEmpty()) {
                if (price.equals("sorted_by_ascending_price")) {
                    if (!contract.isEmpty()) {
                        if (contract.equals("autoParts")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 1));
                        } else if (contract.equals("appliances")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 2));
                        } else if (contract.equals("toys")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 3));
                        } else if (contract.equals("petSupplies")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 4));
                        }
                    } else {
                        model.addAttribute("search_item",
                                itemService.getByTitleOrderByPriceAsc(search.toLowerCase(),
                                        Float.parseFloat(from), Float.parseFloat(to)));
                    }
                } else if (price.equals("sorted_by_descending_price")) {
                    if (!contract.isEmpty()) {
                        if (contract.equals("autoParts")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 1));
                        } else if (contract.equals("appliances")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 2));
                        } else if (contract.equals("toys")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 3));
                        } else if (contract.equals("petSupplies")) {
                            model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 4));
                        }
                    } else {
                        model.addAttribute("search_item",
                                itemService.getByTitleOrderByPriceDesc(search.toLowerCase(),
                                        Float.parseFloat(from), Float.parseFloat(to)));
                    }
                }
            } else {
                model.addAttribute("search_item",
                        itemService.getByTitleAndPriceGreaterThanEqualAndPriceLessThenEqual(search.toLowerCase(),
                                Float.parseFloat(from), Float.parseFloat(to)));
            }
        } else {
            model.addAttribute("search_item",
                    itemService.getByTitleContainingIgnoreCase(search));
        }

        model.addAttribute("value_search", search);
        model.addAttribute("value_price_from", from);
        model.addAttribute("value_price_to", to);
        return "/item/item";
    }
}
