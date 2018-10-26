package com.JBank.src.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;





@Service
public class StockApiProcessor {
	
	private static HttpURLConnection con;
	private static URL url;
	private static BufferedReader result;
	private static String line;
	private static StringBuffer response = new StringBuffer();
	private static String apiUrl = "https://ws-api.iextrading.com/1.0/tops";
	private static String apiSingleExt = "?symbols=";
	private static JSONParser parser = new JSONParser();
	private static JSONObject item;
	
	public static void getStockData(String stockSym) throws IOException, ParseException {
		url = new URL(apiUrl.concat(apiSingleExt).concat(stockSym));
		con = (HttpURLConnection)  url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type","application/json");
		con.setUseCaches(false);
		con.setDoOutput(true);
		if(con.getResponseCode() == 200) {
			result = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while((line = result.readLine()) != null) {
				JSONArray arr = (JSONArray) parser.parse(line);
				
				for(Object o : arr) {
					item = (JSONObject) o;
					System.out.println(item.toString());
					double price = (double) item.get("lastSalePrice");
					
				}
			}
		}
		result.close();
	}
	
	public static void getAllStockData() throws IOException {
		url = new URL(apiUrl);
		con = (HttpURLConnection)  url.openConnection();
		con.setRequestMethod("GET");
		status = con.getResponseCode();
		result = new BufferedReader(new InputStreamReader(con.getInputStream()));
		while((line = result.readLine()) != null) {
			System.out.println(line);
		}
	}
	
}
