package com.company.app;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData implements WriteRetrievedData{

    @Override
    public String writeDataToDB(String[] dataRecord) {
        return null;
    }

    @Override
    public String writeDataToFile(String[] dataRecord) {
        String targetFile = "exchange_rates.txt";
        JSONObject obj = new JSONObject();


        for (int i = 0; i < dataRecord.length ; i++) {
            obj.put(i+1, dataRecord[i]);
        }
        try (
                BufferedWriter file = new BufferedWriter(new FileWriter(targetFile, true));

                ){
            file.write(obj.toJSONString());
            file.newLine();
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "Data was stored! - " + obj;
    }


}
