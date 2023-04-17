package com.example.diploma.repositories;

import com.example.diploma.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByPersonId(int id);

    @Modifying
    @Query(value = "delete from item_cart where item_id=?1", nativeQuery = true)
    void deleteCartByItemId(int id);


    @Query(value = "select * from item_cart where item_id = ?1", nativeQuery = true)
    List<Cart> findCartsByItemId(int id);

}