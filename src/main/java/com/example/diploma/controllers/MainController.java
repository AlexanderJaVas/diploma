package com.example.diploma.controllers;

import com.example.diploma.enums.OrderStatus;
import com.example.diploma.models.Cart;
import com.example.diploma.models.Item;
import com.example.diploma.models.Order;
import com.example.diploma.models.Person;
import com.example.diploma.security.PersonDetails;
import com.example.diploma.services.CartService;
import com.example.diploma.services.ItemService;
import com.example.diploma.services.OrderService;
import com.example.diploma.services.PersonService;
import com.example.diploma.utils.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    private final PersonValidator personValidator;
    private final PersonService personService;
    private final ItemService itemService;
    private final CartService cartService;
    private final OrderService orderService;

    public MainController(PersonValidator personValidator, PersonService personService,
                          ItemService itemService, CartService cartService, OrderService orderService) {
        this.personValidator = personValidator;
        this.personService = personService;
        this.itemService = itemService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/personal_account")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        String role = personDetails.getPerson().getRole();
        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        model.addAttribute("items", itemService.getAllItems());
        return "/user/index";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") Person person) {
        return "security/registration";
    }

    @PostMapping("/registration")
    public String resultOfRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "security/registration";
        }
        personService.register(person);
        return "redirect:/personal_account";
    }

    @GetMapping("/personal_account/item/info/{id}")
    public String infoItem(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "/user/info_item";
    }

    @PostMapping("/personal_account/item/search")
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
                        switch (contract) {
                            case "autoParts" -> model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 1));
                            case "appliances" -> model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 2));
                            case "toys" -> model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 3));
                            case "petSupplies" -> model.addAttribute("search_item",
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
                        switch (contract) {
                            case "autoParts" -> model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 1));
                            case "appliances" -> model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 2));
                            case "toys" -> model.addAttribute("search_item",
                                    itemService.getByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
                                            Float.parseFloat(from), Float.parseFloat(to), 3));
                            case "petSupplies" -> model.addAttribute("search_item",
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
        return "user/index";
    }

    @GetMapping("/cart/add/{id}")
    public String addItemToCart(@PathVariable("id") int id, Model model) {
        Item item = itemService.getItemById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId();
        Cart cart = new Cart(personId, item.getId());
        cartService.saveCart(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId();
        List<Cart> cartList = cartService.getCartByPersonId(personId);
        List<Item> itemList = new ArrayList<>();
        for (Cart cart : cartList) {
            itemList.add(itemService.getItemById(cart.getItemId()));
        }
        float price = 0.0F;
        for (Item item : itemList) {
            price += item.getPrice();
        }
        model.addAttribute("price", price);
        model.addAttribute("cart_item", itemList);
        return "/user/cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String deleteItemFromCart(Model model, @PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId();
        List<Cart> cartList = cartService.getCartByPersonId(personId);
        List<Item> itemList = new ArrayList<>();
        for (Cart cart : cartList) {
            itemList.add(itemService.getItemById(cart.getItemId()));
        }
        cartService.deleteCartByItemId(id);
        return "redirect:/cart";
    }

    @GetMapping("/order/create")
    public String order() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        int personId = personDetails.getPerson().getId();
        List<Cart> cartList = cartService.getCartByPersonId(personId);
        List<Item> itemList = new ArrayList<>();
        for (Cart cart : cartList) {
            itemList.add(itemService.getItemById(cart.getItemId()));
        }
        float price = 0.0F;
        for (Item item : itemList) {
            price += item.getPrice();
        }
        String orderNumber = UUID.randomUUID().toString();
        for (Item item : itemList) {
            Order newOrder = new Order(orderNumber, item, personDetails.getPerson(), 1, item.getPrice(), OrderStatus.Оформлен);
            orderService.saveOrder(newOrder);
            cartService.deleteCartByItemId(item.getId());
        }
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String userOrder(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        List<Order> orderList = orderService.getOrderByPerson(personDetails.getPerson());
        model.addAttribute("orders", orderList);
        return "/user/orders";
    }

    @GetMapping("/orders/history")
    public String userOrdersHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        List<Order> orderList = orderService.getOrdersHistoryByPerson(personDetails.getPerson());
        model.addAttribute("orders", orderList);
        return "/user/orders_history";
    }
}