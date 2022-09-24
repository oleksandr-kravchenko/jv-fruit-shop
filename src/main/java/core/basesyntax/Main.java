package core.basesyntax;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.service.FruitTransaction;
import core.basesyntax.service.TransactionParseService;
import core.basesyntax.service.TransactionParseServiceImpl;
import core.basesyntax.service.filesoperation.FileReader;
import core.basesyntax.service.filesoperation.FileReaderImpl;
import core.basesyntax.service.operationwithfruits.*;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final String INPUT_FILE = "src/main/resources/input_file";
    private static final String REPORT_FILE = "src/main/resources/report_file";

    public static void main(String[] arg) {
        StorageDao storageDao = new StorageDaoImpl();
        Map<FruitTransaction.Operation, OperationHandler> strategy = new HashMap<>();
        strategy.put(FruitTransaction.Operation.BALANCE, new BalanceOperationHandler());
        strategy.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperationHandler());
        strategy.put(FruitTransaction.Operation.RETURN, new ReturnOperationHandler());
        strategy.put(FruitTransaction.Operation.SUPPLY, new SupplyOperationHandler());
        OperationStrategy operationStrategy = new OperationStrategyImpl(strategy);


        //Import data
        FileReader fileReader = new FileReaderImpl();
        List<String> dailyTransactionList = fileReader.read(INPUT_FILE);
        //Parse transactions
        TransactionParseService parserService = new TransactionParseServiceImpl();
        List<FruitTransaction> transactionList = parserService.transactionParse(dailyTransactionList);
        //Transaction processing
        for (FruitTransaction fruitTransaction: transactionList) {
            strategy.get(fruitTransaction.getOperation());
         }


        System.out.println("test");

    }
}
