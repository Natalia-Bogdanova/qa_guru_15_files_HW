package com.bogdanova.modelForJson;
public class ItemsModel {
    public int documentNumber;
    public String invoiceDate;
    public static class Items {
        public static int baseAmount;
        public static String baseUnitId;
        public static int price;
        public static String name;
        public static String productId;
        public static String stockType;
    }
}