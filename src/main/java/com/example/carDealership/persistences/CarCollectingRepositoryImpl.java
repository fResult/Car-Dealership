package com.example.carDealership.persistences;

import com.example.carDealership.domains.CarCollectingRepository;
import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.warehouse.Stock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CarCollectingRepositoryImpl implements CarCollectingRepository {

    private final CarCollectionRepository carCollectionRepository;
    private final StockRepository stockRepository;

    public CarCollectingRepositoryImpl(CarCollectionRepository carCollectionRepository, StockRepository stockRepository) {
        this.carCollectionRepository = carCollectionRepository;
        this.stockRepository = stockRepository;
    }

    /* Note:
     * The method signature might be DomainEntity -> DomainEntity.
     * And when saving the data to the DB, we need to map it to the DAO Entity first.
     */
    @Override
    public CarCollection saveCarCollection(CarCollection entity) {
        return carCollectionRepository.save(entity);
    }

    @Override
    public Stock saveStock(Stock entity) {
        return stockRepository.save(entity);
    }

    @Override
    public Optional<CarCollection> findCarCollectionById(long id) {
        return carCollectionRepository.findById(id);
    }

    @Override
    public Optional<Stock> findStockByModel(String model) {
        var stocks = stockRepository.findByModel(model);

        if (stocks.isEmpty()) return Optional.empty();

        return Optional.of(stockRepository.findByModel(model).getFirst());
    }
}
