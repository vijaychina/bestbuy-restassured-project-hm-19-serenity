package com.bestbuy.constants;


public class EndPoints {

    /**
     * This is Endpoints of product api
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_PRODUCT = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/{productID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/{productID}";
    public static final String DELETE_PRODUCT_BY_ID = "/{productID}";

    /**
     * This is Endpoints of store api
     */
    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_STORES ="/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/{storeID}";
    public static final String UPDATE_STORE_BY_ID = "/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/{storeID}";

    /**
     * This is Endpoints of Authentication api
     */

    public static final String AUTHENTICATE = "/api";

}
