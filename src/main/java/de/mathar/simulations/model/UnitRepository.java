package de.mathar.simulations.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnitRepository extends MongoRepository<Unit, String> {

    public Unit findByName(UnitName name);
}
