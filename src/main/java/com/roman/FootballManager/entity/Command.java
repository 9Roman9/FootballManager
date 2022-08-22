package com.roman.FootballManager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Command implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true,nullable = false)
    private String name;
    @Column(nullable = false)
    private int fee;
    @Column(nullable = false)
    private long money;

    public Command() {
    }

    public Command(String name, int fee, long money) {
        this.name = name;
        this.fee = fee;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}