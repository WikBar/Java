package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJsonFromInternet {
    private String url;

    private String date;

    private List<JsonNode> jsonRates=new ArrayList<>();

    public ReadJsonFromInternet(String url) throws Exception {
        this.url=url;
    }

    public void setDate() throws Exception {

        ObjectMapper objectMapper=new ObjectMapper();
        String JsonString= readUrl(this.url);
        JsonNode jsonNode= objectMapper.readTree(JsonString);
        this.date = jsonNode.get(0).get("effectiveDate").asText();// asText without "" asString with ""
    }

    public void setJsonRates()throws Exception{

        ObjectMapper objectMapper=new ObjectMapper();
        String JsonString= readUrl(this.url);
        JsonNode jsonNode= objectMapper.readTree(JsonString).get(0).get("rates");
        for ( JsonNode objNode : jsonNode) {
            jsonRates.add(objNode);
            System.out.println(objNode.get("code").asText() + " : " +objNode.get("mid"));
        }
    }


    public String getDate() {
        return date;
    }
    public List<JsonNode> getJsonRates() {
        return jsonRates;
    }

    private String readUrl(String urlString) throws Exception {

        BufferedReader reader = null;
        int read;
        char[] chars = new char[1024];
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}
