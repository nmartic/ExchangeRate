package com.company.app;


import java.util.Scanner;
import java.util.Timer;

public class App
{
    public static void main( String[] args )
    {

        System.out.print("Enter check interval (minutes): ");
        Scanner sc = new Scanner(System.in);
        String minutes = sc.nextLine();

        double checkPeriod = Double.parseDouble(minutes) * 60000;


        System.out.println("Started to fetch data from " + ExchangeRateDataSource.EXCHANGE_RATE_SOURCE);
        Timer t = new Timer();
        SchedulerTask sTask = new SchedulerTask();
        t.scheduleAtFixedRate(sTask, 0 , (long) checkPeriod);



    }
}
