package com.example.coursework.controllers;

import com.example.coursework.enums.OrderStatus;
import com.example.coursework.models.*;
import com.example.coursework.services.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value(value = "${upload.path}")
    private String uploadPath;
    private final ItemService itemService;
    private final CategoryService categoryService;
    private final PersonService personService;
    private final OrderService orderService;


    public AdminController(CategoryService categoryService, ItemService itemService, PersonService personService,
                           OrderService orderService) {
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.personService = personService;
        this.orderService = orderService;
    }

    @GetMapping()
    public String admin(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "/admin/admin";
    }

    @GetMapping("/item/add")
    public String addItem(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("category", categoryService.getAllCategories());
        return "item/add_item";
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute("item") @Valid Item item,
                          BindingResult bindingResult,
                          @RequestParam("file_one") MultipartFile file_one,
                          @RequestParam("file_two") MultipartFile file_two,
                          @RequestParam("file_three") MultipartFile file_three,
                          @RequestParam("file_four") MultipartFile file_four,
                          @RequestParam("file_five") MultipartFile file_five,
                          int category, Model model) throws IOException {
        Category category_db = categoryService.getCategoryById(category);
        if (bindingResult.hasErrors()) {
            model.addAttribute("category", categoryService.getAllCategories());
            return "item/add_item";
        }
        saveImageFile(file_one, item);
        saveImageFile(file_two, item);
        saveImageFile(file_three, item);
        saveImageFile(file_four, item);
        saveImageFile(file_five, item);
        itemService.saveItem(item, category_db);
        return "redirect:/admin";

    }

    @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        if (itemService.isItemUsed(id)) {
            return "admin/item_deleting_error";
        }
        itemService.deleteItem(id);
        return "redirect:/admin";
    }

    @GetMapping("/item/edit/{id}")
    public String editItem(Model model, @PathVariable("id") int id) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("category", categoryService.getAllCategories());
        return "item/edit_item";
    }

    @PostMapping("/item/edit/{id}")
    public String editItem(@ModelAttribute("item") @Valid Item item,
                           BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "item/edit_item";
        }
        itemService.updateItem(id, item);
        return "redirect:/admin";
    }

    private void saveImageFile(MultipartFile fileName, Item item) throws IOException {

        if (fileName != null) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileUuid = UUID.randomUUID().toString();
            String resultFileName = fileUuid + "." + fileName.getOriginalFilename();
            fileName.transferTo(new File(uploadPath + "/" + resultFileName));
            com.example.coursework.models.Image image = new Image();
            image.setItem(item);
            image.setFileName(resultFileName);
            item.addImageToItem(image);
        }
    }

    @GetMapping("/users_info")
    public String usersInfo(Model model) {
        model.addAttribute("persons", personService.getAllPersonsSortedAscById());
        return "admin/users_info";
    }

    @PostMapping("/edit_user/{id}")
    public String editPersonRole(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        if (person.getId() == 1) {
            return "redirect:/admin/users_info";
        }
        personService.updatePersonRole(id);
        return "redirect:/admin/users_info";
    }

    @GetMapping("/orders_info")
    public String ordersInfo(Model model) {
        model.addAttribute("orders", orderService.getAllOrdersSortAscById());
        return "/admin/orders_info";
    }

    @GetMapping("/orders/info/{id}")
    public String infoOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "/admin/order_details";
    }

    @PostMapping("/edit_order_status/{id}")
    public String editOrderStatus(@PathVariable("id") int id,
                                  @RequestParam(name = "orderStatus") OrderStatus orderStatus,
                                  @ModelAttribute("order") Order order,
                                  Model model) {
        model.addAttribute("orderStatus", orderStatus);
        orderService.updateOrderStatus(id, orderStatus);
        return "redirect:/admin/orders_info";
    }

    @PostMapping("/order/search")
    public String orderSearch(@RequestParam("search") String search, Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        if (orderService.getByLastFourSigns(search) == null) {
            model.addAttribute("error", "error");
        }
        model.addAttribute("search_order", orderService.getByLastFourSigns(search));
        return "/admin/orders_info";
    }
}