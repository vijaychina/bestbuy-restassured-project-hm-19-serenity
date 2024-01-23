package com.bestbuy.crudsuite;

import com.bestbuy.bestbuyinfo.StoreSteps;
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
public class StoreCRUDTestWithSteps extends TestBase {
    static String name = "ajay" + TestUtils.getRandomValue();
    static String type = "sendBox"  + TestUtils.getRandomValue();
    static String address = "A"  + TestUtils.getRandomValue();
    static String address2 = "The Chine"  + TestUtils.getRandomValue();
    static String city = "London";
    static String state = "London";
    static String zip = "HA4 0DF";
    static Double lat = 44.969658;
    static Double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;
    @Steps
    StoreSteps steps;

    @Title("This will create a new Store")
    @Test
    public void test001() {
        ValidatableResponse response = steps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().statusCode(201);
        storeId = response.extract().path("id");
        System.out.println("Product ID number is: " + storeId);
    }
    @Title("Verify if the Store was added to the application")
    @Test
    public void test002() {

        HashMap<String, Object> storeMap = steps.getStoreInfoByStoreId(storeId);

        Assert.assertThat(storeMap, hasValue(name));

        storeId = (int) storeMap.get("id");
    }
    @Title("Update store by store ID")
    @Test
    public void test003() {
        name = "Vrishna" + TestUtils.getRandomValue();
        type = "OFF-Licence" + TestUtils.getRandomValue();
        steps.updateStoreById(storeId, name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);

        //Verifying that student is updated successfully
        HashMap<String, Object> storeMap = steps.getStoreInfoByStoreId(storeId);

        Assert.assertThat(storeMap, hasValue(name));

    }
    @Title("Delete Product by product ID")
    @Test
    public void test04() {
        steps.deleteStoreByID(storeId).statusCode(200);

        steps.getStoreInfoById(storeId).statusCode(404);
    }

}
