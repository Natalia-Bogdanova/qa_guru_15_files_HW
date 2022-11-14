package com.bogdanova.modelForJson;

import java.util.List;
public class ItemsModel {
        public int number;
        public String time;
        public String vendorName;
        public Items items;
        public List<String> warehouse;

        public static class Items {
            public int BaseAmount;
            public double price;
            public int IdProduct;
            public String productName;
            public boolean isActive;
        }
    }