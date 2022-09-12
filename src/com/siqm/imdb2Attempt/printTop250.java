package com.siqm.imdb2Attempt;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class printTop250 {

	public static void main(String[] args) throws Exception {

		String json = getContent(args[0]);
		
		
	}
	
	public static String[] splitMovies(String json) {
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
		
		if (!matcher.matches()) {
			throw new IllegalArgumentException("no match found " + json);
		}
		var array = matcher.group(1).split("\\},\\{");
		int size = array.length - 1;
		array[0] = array[0].substring(1);
		array[size] = array[size].replace("}", "");
		
		return array;
	}
	
	public static String getContent(String args) throws Exception {
		
		URI apiIMDB = new URI("https://imdb-api.com/en/API/Top250Movies/" + args);
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();
		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		
		return response.body();
	}

}
