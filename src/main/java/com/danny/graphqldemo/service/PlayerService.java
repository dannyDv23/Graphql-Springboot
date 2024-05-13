package com.danny.graphqldemo.service;

import com.danny.graphqldemo.model.Player;
import com.danny.graphqldemo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    public List<Player> players = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);


    public List<Player> findAll(){
        return players;
    }
    // find by id
    public Optional<Player> findOne(Integer id){
        return players.stream().filter(player -> player.Id() == id).findFirst();
    }
    // create player
    public Player create(String name, Team team){
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    // delete
    public Player delete(Integer id){
        Player player = players.stream().filter(c -> c.Id() == id).findFirst().orElseThrow(() -> new IllegalArgumentException());
        players.remove(player);
        return player;
    }
    //update
    public Player update(Integer id, String name, Team team){
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(c -> c.Id() == id).findFirst();
        if (optional.isPresent()){
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index,updatedPlayer);
        }else {
            throw new IllegalArgumentException("Invalid Player");
        } return updatedPlayer;
    }
    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(), "Dao Van Dat", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Pham Nam Phuong", Team.GT));
        players.add(new Player(id.incrementAndGet(), "Nguyen Ngoc Ha", Team.DC));
        players.add(new Player(id.incrementAndGet(), "Kieu Thi Ngoc Dung", Team.CSK));

    }

}
