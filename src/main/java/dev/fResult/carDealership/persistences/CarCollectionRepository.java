package dev.fResult.carDealership.persistences;

import dev.fResult.carDealership.domains.carCollecting.entities.CarCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCollectionRepository extends CrudRepository<CarCollection, Long> {}
