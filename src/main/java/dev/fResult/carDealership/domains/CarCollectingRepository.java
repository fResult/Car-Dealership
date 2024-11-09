package dev.fResult.carDealership.domains;

import dev.fResult.carDealership.domains.carCollecting.entities.CarCollection;
import dev.fResult.carDealership.domains.warehouse.Stock;

import java.util.Optional;

/* Note:
 * This interface is the bottom line spec that can work in the Car Collecting domain.
 * So, we need to implements at least these all method first.
 */
public interface CarCollectingRepository {
    CarCollection saveCarCollection(CarCollection entity);

    Stock saveStock(Stock entity);
    // Loading saveLoading(Loading entity);

    Optional<CarCollection> findCarCollectionById(long id);

    Optional<Stock> findStockByModel(String model);
    // Optional<Loading> findLoadingByXxx(Xxx xxx);
}
