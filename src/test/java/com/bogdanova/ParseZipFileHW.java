package com.bogdanova;

import com.bogdanova.modelForJson.ItemsModel;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseZipFileHW {

        ClassLoader cl = ParseZipFileHW.class.getClassLoader();

        @DisplayName("Checking PDF-file from ZIP")
        @Test
        void testPDF() throws Exception {
                ZipFile zipfile = new ZipFile(new File("src/test/resources/HWParseZip.zip"));
                try (InputStream is = cl.getResourceAsStream("HWParseZip.zip");
                     ZipInputStream zis = new ZipInputStream(is)) {
                        ZipEntry entry;
                        while ((entry = zis.getNextEntry()) != null) {
                                if (entry.getName().contains(".pdf")) {
                                        try (InputStream inputStream = zipfile.getInputStream(entry)) {
                                                PDF pdf = new PDF(inputStream);
                                                assertThat(pdf.author).isEqualTo("Natali");
                                                assertThat(pdf.numberOfPages).isEqualTo(1);
                                        }
                                }
                        }
                }
        }

        @DisplayName("Checking XLSX-file from ZIP")
        @Test
        void zipXlsTest() throws Exception {
                ZipFile zipfile = new ZipFile(new File("src/test/resources/HWParseZip.zip"));
                try (InputStream is = cl.getResourceAsStream("HWParseZip.zip");
                     ZipInputStream zis = new ZipInputStream(is)) {
                        ZipEntry entry;
                        while ((entry = zis.getNextEntry()) != null) {
                                if (entry.getName().contains(".xlsx")) {
                                        try (InputStream inputStream = zipfile.getInputStream(entry)) {
                                                XLS xls = new XLS(inputStream);
                                                AssertionsForClassTypes.assertThat(
                                                        xls.excel.getSheetAt(0)
                                                                .getRow(1)
                                                                .getCell(1).getStringCellValue()
                                                ).isEqualTo("0104665306490045215r5IBU0732117");
                                        }
                                }
                        }
                }
        }

        @DisplayName("Checking CSV-file from ZIP")
        @Test
        void zipCsvTest() throws Exception {
                ZipFile zipfile = new ZipFile(new File("src/test/resources/HWParseZip.zip"));
                try (InputStream is = cl.getResourceAsStream("HWParseZip.zip");
                     ZipInputStream zis = new ZipInputStream(is)) {
                        ZipEntry entry;
                        while ((entry = zis.getNextEntry()) != null) {
                                if (entry.getName().contains(".csv")) {
                                        try (InputStream inputStream = zipfile.getInputStream(entry);
                                             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
                                                List<String[]> content = reader.readAll();
                                                String[] row = content.get(2);
                                                assertThat(row[0]).isEqualTo("Nothing is clear");
                                                assertThat(row[1]).isEqualTo("Very interesting");
                                        }
                                }
                        }
                }
        }
        @DisplayName("Checking JSON-file with model")
                @Test
                void jsonJackson() throws Exception {
                        InputStream is = cl.getResourceAsStream("HW_json.json");
                        Gson gson = new Gson();
                        ItemsModel itemsModel = gson.fromJson(new InputStreamReader(is), ItemsModel.class);
                        assertThat(itemsModel.number).isEqualTo(1488);
                        assertThat(itemsModel.time).isEqualTo("10.10.22 16:40:00");
                        assertThat(itemsModel.vendorName).isEqualTo("Nestle");
                        assertThat(itemsModel.warehouse.get(0)).isEqualTo("DC01");
                        assertThat(itemsModel.warehouse.get(1)).isEqualTo("DC02");
                        assertThat(itemsModel.items.BaseAmount).isEqualTo(30);
                        assertThat(itemsModel.items.price).isEqualTo(20.6);
                        assertThat(itemsModel.items.IdProduct).isEqualTo(544331);
                        assertThat(itemsModel.items.productName).isEqualTo("Milk");
                        assertThat(itemsModel.items.isActive).isTrue();
                }
        }
