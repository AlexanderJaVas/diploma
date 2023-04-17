package com.example.diploma.repositories;

import com.example.diploma.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByLogin(String login);

    @Query(value = "select * from person order by id", nativeQuery = true)
    List<Person> findAllPersonsSortedAscById();
}

