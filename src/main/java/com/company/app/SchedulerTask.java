package com.company.app;

import java.util.TimerTask;

public class SchedulerTask extends TimerTask {

    public SchedulerTask() {

    }

    @Override
    public void run() {

        String sourceUrl = ExchangeRateDataSource.EXCHANGE_RATE_SOURCE;
        RetrieveData dataRetrieved = new Retrieval();
        WriteRetrievedData dataStored = new WriteData();


        String data[] = new String[0];
        String dataStore = null;
        data = dataRetrieved.getData(sourceUrl);
        dataStore = dataStored.writeDataToFile(data);

    }
}
