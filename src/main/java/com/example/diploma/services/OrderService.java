package com.example.diploma.services;

import com.example.diploma.enums.OrderStatus;
import com.example.diploma.models.Order;
import com.example.diploma.models.Person;
import com.example.diploma.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrderByPerson(Person person) {
        return orderRepository.findByPerson(person);
    }

    public Order getOrderById(int id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public boolean itemByIdInOrders(int id) {
        List<Order> optionalOrder = orderRepository.findOrderByItemId(id);
        return !optionalOrder.isEmpty();
    }

    public List<Order> getAllOrdersSortAscById() {
        return orderRepository.findAllSortedAscById();
    }

    @Transactional
    public void updateOrderStatus(int id, OrderStatus orderStatus) {
        Optional<Order> updatedOrder = orderRepository.findById(id);
        if (updatedOrder.isPresent()) {
            Order newOrder = updatedOrder.get();
            newOrder.setOrderStatus(orderStatus);
            orderRepository.save(newOrder);
        }
    }

    public List<Order> getByLastFourSigns(String search) {
        if (!search.matches("^[a-zA-z0-9]{4}$")) {
            return null;
        }
        return orderRepository.findByNumberEndingWith(search);
    }

    public List<Order> getOrdersHistoryByPerson(Person person) {
        return orderRepository.getOrdersHistoryByPerson(person);
    }
}
