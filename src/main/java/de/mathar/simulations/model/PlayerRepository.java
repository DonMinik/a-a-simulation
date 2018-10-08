package de.mathar.simulations.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository  extends MongoRepository<Player, String> {


    public Player findByAttackerIs(boolean isAttacker);
}
