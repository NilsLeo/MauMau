package de.htwberlin.kbe.gruppe4.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "virtual_players")
public class VirtualPlayer extends Player {

    public VirtualPlayer() {}

    public VirtualPlayer(String name) {
        super(name);
    }
}