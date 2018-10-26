package com.JBank.src.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;





@Service
public class StockApiProcessor {
	
	private static String apiUrl = "https://ws-api.iextrading.com/1.0/tops";
	private static String apiSingleExt = "?symbols=";
	private static JSONArray stockJson = null;
	
	private static JSONArray getStockAsJson(String stockSym) throws IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		HttpURLConnection con;
		URL url;
		String line;
		
		if(stockSym != null) {
			url = new URL(apiUrl.concat(apiSingleExt).concat(stockSym));
		} else {
			url = new URL(apiUrl);
		}
		con = (HttpURLConnection)  url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type","application/json");
		con.setUseCaches(false);
		con.setDoOutput(true);
		if(con.getResponseCode() == 200) {
			BufferedReader result = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while((line = result.readLine()) != null) {
				stockJson = (JSONArray) parser.parse(line);
			}
			result.close();

		} 
		
		return stockJson;
	}
	
	public static Stock getStockData(String stockSym) throws IOException, ParseException {
		JSONObject stockJsonSingle = (JSONObject) getStockAsJson(stockSym).get(0);
		System.out.println(stockJsonSingle.toString());
		return new Stock(stockSym, (double)stockJsonSingle.get("lastSalePrice"));
	}
	
	public static Stock[] getAllStockData() throws IOException, ParseException {
		stockJson = getStockAsJson(null);
		Stock[] stockArray = new Stock[stockJson.size()];
		double price;
		int i = 0;
		for(Object o : stockJson) {
			JSONObject obj = (JSONObject) o;
			try {
				price = (double) obj.get("lastSalePrice");
			} catch (ClassCastException e){
				price = ((Long) obj.get("lastSalePrice")).doubleValue();
			}
			stockArray[i] = new Stock((String) obj.get("symbol"), price);
					i += 1;
		}
		
		return stockArray;
	}
	
}
