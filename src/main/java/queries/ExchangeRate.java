package queries;

import models.ApiResponse;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ExchangeRate {

    public static ApiResponse query(String pair1, String pair2) {
        String apiKey = YOUR-API-KEY;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                            + pair1 + "/" + pair2))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            return gson.fromJson(json, ApiResponse.class);

        } catch (Exception error) {
            System.out.println("Error API: " + error.getMessage());
        }
        return new ApiResponse("Error","","",0);
    }
}
