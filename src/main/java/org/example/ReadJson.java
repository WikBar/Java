package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadJson {

    JSONArray RatesList;

    public ReadJson(String fileName){
        ReadFile(fileName);
    }

    private void ReadFile(String fileName) {
        {
            JSONParser JSONParser= new JSONParser();
            try {
                Object obj = JSONParser.parse(new FileReader(fileName));

                JSONObject Courses = (JSONObject) obj;
                JSONArray RatesList = (JSONArray) Courses.get("rates");

                this.RatesList=RatesList;

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    private static void parseRateObject(JSONObject rates)
    {
        //Get Currency name
        String currency = (String) rates.get("currency");
        //Get Code of a currency
        String code = (String) rates.get("code");
        //Get Currency Average value
        Double Average = (Double) rates.get("mid");
        System.out.println(currency);
        System.out.println(code + " " +Average);
    }

    public double getValueByCode(String Code){

        for (int i=0;i<this.RatesList.size();i++){
            JSONObject obj=(JSONObject) this.RatesList.get(i);
            if(obj.get("code").equals(Code)){
                return (Double) obj.get("mid");
            }
        }
        return 0;
    }

    public ArrayList<String> getNameWithCode() {

        ArrayList<String> namesWithCode= new ArrayList<>();
        for (int i=0;i<this.RatesList.size();i++){
            JSONObject obj=(JSONObject) this.RatesList.get(i);
                namesWithCode.add(obj.get("currency")+" "+obj.get("code"));
        }
        namesWithCode.forEach(System.out::println);
        return namesWithCode;
        }
    }

