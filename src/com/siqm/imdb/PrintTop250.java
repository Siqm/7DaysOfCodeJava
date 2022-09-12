package com.siqm.imdb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintTop250 {

	public static void main(String[] args) throws Exception {

		String json = getResultsFromApi(args[0]);

		String[] moviesArray = parseJsonMovies(json);
		
		List<String> titles = parseTitles(moviesArray);
		
		List<String> urlImages = parseUrlImages(moviesArray);
		System.out.println(moviesArray);
		
	}

	private static List<String> parseUrlImages(String[] moviesArray) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<String> parseTitles(String[] moviesArray) {
		
		return null;
	}

	private static String[] parseJsonMovies(String json) {
		Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);
		
		if (!matcher.matches()) {
			throw new IllegalArgumentException("no match in " + json);
		}
		
		String[] moviesArray = matcher.group(1).split("\\},\\{");
		System.out.println(moviesArray[0]);
//		for(String movie : moviesArray) {
//			System.out.println(movie);
//		}
		moviesArray[0] = moviesArray[0].replace("{", "");
		int last = moviesArray.length - 1;
		moviesArray[last] = moviesArray[last].replace("}", "");
		
		return moviesArray;
	}

	private static String getResultsFromApi(String apiKey) throws Exception {

		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey.trim());

		HttpResponse<String> response = null;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

		response = client.send(request, BodyHandlers.ofString());
		
		String json = response.body();
		
		return json;
	}

}
