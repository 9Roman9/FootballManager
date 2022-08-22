package com.roman.FootballManager.repository;

import com.roman.FootballManager.entity.Command;
import com.roman.FootballManager.entity.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Modifying
    @Query(value = "update player p set p.first_name = :firstName, p.last_name = :lastName," +
            "p.age = :age, p.experience = :experience, p.command = :command where p.id = :id", nativeQuery = true)
    void updatePlayer(@Param("id") long id, @Param("firstName") String firstName,
                      @Param("lastName") String lastName, @Param("age") int age,
                      @Param("experience") int experience, @Param("command") String command);

    @Query(value = "select p.age from player p where p.id = :id", nativeQuery = true)
    int getAgeOfPlayer(@Param("id") long id);

    @Query(value = "select p.experience from player p where p.id = :id", nativeQuery = true)
    int getExperienceOfPlayer(@Param("id") long id);

    @Modifying
    @Query(value = "update player p set p.command = :command where p.id = :id", nativeQuery = true)
    void changeCommandOfPlayer(@Param("id") long id, @Param("command") String nameCommand);
}
