package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Builder {
    public static String baseURI = "https://rahulshettyacademy.com";

    public static RequestSpecification loginRequestSpecification()
    {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }
    public static RequestSpecification authRequestSpecification(String token)
    {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addHeader("Authorization",token )
                .build();
    }
    public static RequestSpecification orderRequestSpecification(String token)
    {
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addHeader("Authorization",token )
                .setContentType(ContentType.JSON)
                .build();
    }

}
