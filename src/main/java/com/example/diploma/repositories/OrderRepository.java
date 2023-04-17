package com.example.diploma.repositories;

import com.example.diploma.models.Order;
import com.example.diploma.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByPerson(Person person);

    @Query(value = "select * from orders where item_id = ?1", nativeQuery = true)
    List<Order> findOrderByItemId(int id);

    @Query(value = "select * from orders order by id", nativeQuery = true)
    List<Order> findAllSortedAscById();

    List<Order> findByNumberEndingWith(String search);

    @Query(value = "select * from orders where order_status = 3", nativeQuery = true)
    List<Order> getOrdersHistoryByPerson(Person person);
}
