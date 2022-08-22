package com.roman.FootballManager.repository;

import com.roman.FootballManager.entity.Command;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CommandRepository extends CrudRepository<Command, Long> {
    @Modifying
    @Query(value = "update command c set c.name = :newName, c.fee = :fee where c.name = :oldName",nativeQuery = true)
    void updateCommand(@Param("oldName") String oldName, @Param("newName") String newName, @Param("fee") int fee);

    @Query(value = "select * from command c where c.name = :name", nativeQuery = true)
    Command getCommandByName(@Param("name") String name);

    @Modifying
    @Query(value = "update command c set c.money = :money where c.name = :name", nativeQuery = true)
    void setNewSumOfMoney(@Param("name") String name, @Param("money") long money);
}
