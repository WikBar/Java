package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
//Reading JSON with currencies from file
        JFrame frame = new JFrame("Currency converter");
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700,800);

        String url= "http://api.nbp.pl/api/exchangerates/tables/A/";
        ReadJson readJson=null;
        try{
            readJson = new ReadJson("C:\\Users\\PAJK\\IdeaProjects\\Projekt\\src\\main\\resources\\pobrane.json");

            URL urlTest=new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlTest.openConnection();
            connection.connect();
            System.out.println("Internet is connected");
            ReadJsonFromInternet readJsonFromInternet= new ReadJsonFromInternet(url);
            readJsonFromInternet.setDate();
            readJsonFromInternet.setJsonRates();
            System.out.println(readJsonFromInternet.getJsonRates());
            System.out.println(readJsonFromInternet.getDate());
            CurrencyConverter currencyConverter=new CurrencyConverter(readJson);

            System.out.println("Witaj wymien swoje waluty");
            Scanner sc = new Scanner(System.in);
            System.out.println("Chcesz dokonac wymiany w zlotowkach? y/n");

            if(sc.next().equals("y")) {
                currencyConverter.inPLN();
            }else{
                currencyConverter.InOtherCurrency();
            }
        }catch(MalformedURLException e){
            System.out.println("Internet is not connected");
        }catch (UnknownHostException uhe) {
            System.out.println("Internet is not connected");
        } catch (IOException ioe) {
            System.out.println("Internet is not connected");
        }



    }
}