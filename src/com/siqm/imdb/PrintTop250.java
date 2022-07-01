package com.siqm.imdb;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;


public class PrintTop250 {

	public static void main(String[] args) throws URISyntaxException {
		
		if (args.length < 1) {
			System.out.println("ERROR: API key must be provided. Exiting...");
			System.exit(-1);
		}
		
		String apiEndpoint = "https://imdb-api.com/en/API/Top250Movies/" + args[0].trim();
		
		HttpResponse<String> response = null;
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI(apiEndpoint))
				.GET()
				.build();
		
		try {
			response = client.send(request, BodyHandlers.ofString());
		} catch (Exception e) {
			System.out.println("[ERROR] Exception when Calling API: " + e.getMessage());
		}
		
		String[] json = response.body().split("\":\"");
		for (String filme : json) {
			System.out.println(filme);
		}
		
	}

}
