package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.strategy.OperationStrategy;
import java.util.List;

public class ShopServiceImpl implements ShopService {
    private OperationStrategy operationStrategy;

    public ShopServiceImpl(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public void process(List<FruitTransaction> transactions) {
        for (FruitTransaction fruitTransaction : transactions) {
            if (fruitTransaction.getQuantity() < 0) {
                throw new IllegalArgumentException("Quantity cannot be negative: "
                        + fruitTransaction.getQuantity());
            }
            operationStrategy.getHandler(fruitTransaction.getOperation())
                    .apply(fruitTransaction.getFruit(), fruitTransaction.getQuantity());
        }
    }
}
