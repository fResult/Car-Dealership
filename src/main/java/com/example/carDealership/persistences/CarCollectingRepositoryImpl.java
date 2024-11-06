package com.example.carDealership.persistences;

import com.example.carDealership.domains.carCollecting.entities.CarCollection;
import com.example.carDealership.domains.warehouse.Stock;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CarCollectingRepositoryImpl implements com.example.carDealership.domains.CarCollectingRepository {

    private final CarCollectionRepository carCollectionRepository;
    private final StockRepository stockRepository;

    public CarCollectingRepositoryImpl(CarCollectionRepository carCollectionRepository, StockRepository stockRepository) {
        this.carCollectionRepository = carCollectionRepository;
        this.stockRepository = stockRepository;
    }

    /* Note:
     * The method signature might be DomainEntity -> DomainEntity as a Pure Repository approach.
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

        return stocks.stream().findFirst();
    }
}