package com.bestbuy.crudsuite;

import com.bestbuy.bestbuyinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductsCRUDTestWithSteps extends TestBase {
    static String name = "Duracell - AAA Batteries (4-Pack)" + TestUtils.getRandomValue();
    static String type = "HardGood" + TestUtils.getRandomValue();
    static Double price = 5.49;
    static String upc = "041333424019";
    static Double shipping = 0.0;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productId;
    @Steps
    ProductSteps steps;

    @Title("This will create a new Product")
    @Test
    public void test001() {
        ValidatableResponse response = steps.createProduct(name, type, price, upc,
                shipping, description, manufacturer, model, image, url);
        response.log().all().statusCode(201);
        productId = response.extract().path("id");
        System.out.println("Product ID number is: " + productId);
    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test002() {
        HashMap<String, Object> productMap = steps.getProductInfoByProductId(productId);

        Assert.assertThat(productMap, hasValue(name));

        productId = (int) productMap.get("id");
    }

    @Title("Update product by Product ID")
    @Test
    public void test003() {
        name = "Aman" + TestUtils.getRandomValue();
        type = "Chargeable" + TestUtils.getRandomValue();
        steps.updateProductById(productId, name, type, price, upc, shipping, description,
                manufacturer, model, image, url).statusCode(200);

        //Verifying that product is updated successfully
        HashMap<String, Object> studentMap = steps.getProductInfoByProductId(productId);

        Assert.assertThat(studentMap, hasValue(name));


    }

    @Title("Delete Product by product ID")
    @Test
    public void test04() {
        steps.deleteProductByID(productId).statusCode(200);

        steps.getProductInfoById(productId).statusCode(404);
    }
}
