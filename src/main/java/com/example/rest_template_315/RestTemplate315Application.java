package com.example.rest_template_315;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplate315Application {
    static final String apiUrl = "http://94.198.50.185:7081/api/users";
    static RestTemplate restTemplate = new RestTemplate();
    static HttpHeaders headers = new HttpHeaders();
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) {
        SpringApplication.run(RestTemplate315Application.class, args);
        String cok = getUser();
        result.append(createUser(cok)).append(updateUser(cok)).append(deleteUser(cok));
        System.out.println(result);
    }

    public static String getUser() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, httpEntity, String.class);
        String cookieGET = responseEntity.getHeaders().getFirst("set-cookie");
        System.out.println(cookieGET);
        return cookieGET;
    }

    public static String createUser(String cookie) {
        User user = new User((long) 3, "James", "Brown", (byte) 25);
        headers.add("accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, String.class);
        String cookiePOST = responseEntity.getBody();
        System.out.println(cookiePOST);
        return cookiePOST;
    }

    public static String updateUser(String cookie) {
        User user = new User((long) 3, "Thomas", "Shelby", (byte) 30);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.COOKIE, cookie);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.PUT, httpEntity, String.class);
        String cookiePUT = responseEntity.getBody();
        System.out.println(cookiePUT);
        return cookiePUT;
    }

    public static String deleteUser(String cookie) {
        String url = apiUrl + "/3";
        headers.set(HttpHeaders.COOKIE, cookie);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
        String cookieDELETE = responseEntity.getBody();
        System.out.println(cookieDELETE);
        return cookieDELETE;
    }
}
