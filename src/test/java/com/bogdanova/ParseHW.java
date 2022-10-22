package com.bogdanova;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import jdk.internal.jmod.JmodFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseHW {
    ClassLoader cl = ParseHW.class.getClassLoader();

    @Test
    void zipTest() throws Exception {
        Class<Object> cl;
        InputStream is = cl.getResourceAsStream("TestHW.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            Assertions.assertThat(entry.getName()).isEqualTo("Клиники по программе.xlsx");
            Assertions.assertThat(entry.getName()).isEqualTo("Natalia Bogdanova Communication Profile.pdf");
            Assertions.assertThat(entry.getName()).isEqualTo("fileCSV.csv");
            switch (entry.getName()) {
                case "fileCSV.csv":
                    JmodFile zf;
                    try (InputStream inputStream = zf.getInputStream(entry);
                         CSVReader reader = new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(0);
                        String searchWords = row[1];
                        assertThat(searchWords).isEqualTo("Samara");
                    }
                    break;
                case "Клиники по программе.xlsx":
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(1)
                                        .getRow(1)
                                        .getCell(2)
                                        .getStringCellValue()
                        ).isEqualTo("Samara");
                    }
                    break;
                case "Natalia Bogdanova Communication Profile.pdf":
                    try (InputStream inputStream = zf.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.author).isEqualTo("Максим");
                    }
                    break;
            }
        }
    }

}