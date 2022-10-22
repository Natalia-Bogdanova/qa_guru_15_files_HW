package com.bogdanova.modelForJson;

import java.util.ArrayList;

public class ItemsModel {
    public int documentNumber;
    public int invoiceDate;
    public static class Items {
        public int baseAmount;
        public String baseUnitId;
        public int price;
        public String name;
        public int productId;
        public String stockType;
    }
}

//{       "documentNumber": 9999000353,
//        "invoiceDate": "2022-11-12",
//        "items":
//        [{
//        "baseAmount": 10,
//        "baseUnitId": "ST",
//        "price": 55,
//        "name": "Батончик Fitness цел.злаками23,5г",
//        "productId": "000000000003316579",
//        "stockType": "QQ" }]}