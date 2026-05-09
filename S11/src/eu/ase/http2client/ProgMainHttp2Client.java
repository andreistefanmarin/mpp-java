package eu.ase.http2client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProgMainHttp2Client {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        System.out.println(httpClient.version());

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI("https://www.google.com")).GET().build();
            Map<String, List<String>> headers = httpRequest.headers().map();
            headers.forEach((key, value) -> System.out.println(key + ": " + value));

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //System.out.println("HTTP2 response = \n" + httpResponse.body());

            CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture =
                    httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
            Thread.sleep(5000);

            if(httpResponseCompletableFuture.isDone()){
                System.out.println("HTTP response async = \n" + httpResponseCompletableFuture.get().statusCode()
                        + "\n" + httpResponseCompletableFuture.get().body());
            } else {
                System.out.println("Response not received!");
                httpResponseCompletableFuture.cancel(true);
            }

        } catch (URISyntaxException | IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
