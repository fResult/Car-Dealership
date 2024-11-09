package dev.fResult.carDealership.persistences;

import dev.fResult.carDealership.domains.warehouse.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    Optional<Stock> findOneByModel(String model);
}
