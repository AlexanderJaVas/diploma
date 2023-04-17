package com.example.diploma.repositories;

import com.example.diploma.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByTitleContainingIgnoreCase(String title);

    @Query(value = "select * from item where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Item> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, float from, float to);

    @Query(value = "select * from item where (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price", nativeQuery = true)
    List<Item> findByTitleOrderByPriceAsc(String title, float from, float to);

    @Query(value = "select * from item where (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price desc", nativeQuery = true)
    List<Item> findByTitleOrderByPriceDesc(String title, float from, float to);

    @Query(value = "select * from item where category_id = ?4 and(lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price", nativeQuery = true)
    List<Item> findByTitleAndCategoryOrderByPriceAsc(String title, float from, float to, int category);

    @Query(value = "select * from item where category_id = ?4 and(lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '%?1') and (price >= ?2 and price <= ?3) order by price desc", nativeQuery = true)
    List<Item> findByTitleAndCategoryOrderByPriceDesc(String title, float from, float to, int category);

}


