package models;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.*;
import utils.Builder;
import utils.JsonDataLoader;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class LoginRequest {
    public static LoginInfo login() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserEmail("elrayessondos9@gmail.com");
        loginInfo.setUserPassword("i$A26hsRrFm@jhm");

        return loginInfo;
    }

    public static void main(String[] args) {
        //Login
        RequestSpecification newRequestSpec = Builder.loginRequestSpecification();
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200).build();

        LoginResponseInfo responseInfo =
                given().spec(newRequestSpec).body(login()).
                when().post("/api/ecom/auth/login").
                then().spec(responseSpec).extract().response().as(LoginResponseInfo.class);

        //Creat Product
        RequestSpecification reqAuth = Builder.authRequestSpecification(responseInfo.getToken());
        CreateProductResponse createProductResponse =
                given().spec(reqAuth)
                .param("productAddedBy",responseInfo.getUserId())
                .param("productCategory", JsonDataLoader.getValue("Product.productCategory"))
                .param("productSubCategory",JsonDataLoader.getValue("Product.productSubCategory"))
                .param("productPrice",JsonDataLoader.getValue("Product.productPrice"))
                .param("productDescription",JsonDataLoader.getValue("Product.productDescription"))
                .param("productFor",JsonDataLoader.getValue("Product.productFor"))
                .param("productName",JsonDataLoader.getValue("Product.productName"))
                .multiPart("productImage",new File("src/main/resources/iPhone16.png"))
                .when().post("/api/ecom/product/add-product")
                .then().extract().response().as(CreateProductResponse.class);


        //Create Order
        Order order = new Order();
        order.setProductOrderedId(createProductResponse.getProductId());
        order.setCountry("Egypt");
        List<Order> ordersList = new ArrayList<>();
        ordersList.add(order);
        Orders orders = new Orders();
        orders.setOrders(ordersList);
        RequestSpecification rs = Builder.orderRequestSpecification(responseInfo.getToken());

        given().spec(rs).body(orders)
                .when().post("/api/ecom/order/create-order")
                .then().log().all();
        //Delete product
        String productId = createProductResponse.getProductId();
        given().spec(reqAuth).pathParam("productId",productId)
                .when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all();




    }
}
