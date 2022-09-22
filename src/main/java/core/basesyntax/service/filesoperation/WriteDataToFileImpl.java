package core.basesyntax.service.filesoperation;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteDataToFileImpl implements WriteDataToFile {
    private static final String REPORT_HEADER = "fruit,quantity";
    private static final String ERROR_WRITE_MESSAGE = "Can't write data to file ";

    @Override
    public void writeData(String outputFile) {
        StorageDao storageDao = new StorageDaoImpl();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REPORT_HEADER)
                .append(System.lineSeparator())
                .append(storageDao.getAllFruitsFromStorage());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(ERROR_WRITE_MESSAGE + outputFile, e);
        }

    }
}
