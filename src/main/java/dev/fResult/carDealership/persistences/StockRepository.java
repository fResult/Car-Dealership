package dev.fResult.carDealership.persistences;

import dev.fResult.carDealership.domains.warehouse.Stock;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
  Optional<Stock> findOneByModel(String model);
}
