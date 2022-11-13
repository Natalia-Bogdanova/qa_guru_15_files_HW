package com.bogdanova;

import com.bogdanova.modelForJson.ItemsModel;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParseJSONhw {
    ClassLoader cl = ParseJSONhw.class.getClassLoader();
    @DisplayName("Checking JSON-file with model")
    @Test
    void jsonJackson() throws Exception {
        InputStream is = cl.getResourceAsStream("HW_json.json");
        Gson gson = new Gson();
        ItemsModel itemsModel = gson.fromJson(new InputStreamReader(is), ItemsModel.class);
        assertThat(itemsModel.documentNumber).isEqualTo(123);
        assertThat(itemsModel.invoiceDate).isEqualTo("2022-11-12");
        assertThat(ItemsModel.Items.baseAmount).isEqualTo(11);
        assertThat(ItemsModel.Items.baseUnitId).isEqualTo("ST");
        assertThat(ItemsModel.Items.price).isEqualTo(55);
        assertThat(ItemsModel.Items.name).isEqualTo("Cake");
        assertThat(ItemsModel.Items.productId).isEqualTo("000000000003316579");
        assertThat(ItemsModel.Items.stockType).isEqualTo("QQ");
    }
}