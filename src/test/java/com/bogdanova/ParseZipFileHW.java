package com.bogdanova;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
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
                ZipFile zipfile = new ZipFile(new File("src/test/resources/Test_HW.zip"));
                try (InputStream is = cl.getResourceAsStream("Test_HW.zip");
                     ZipInputStream zis = new ZipInputStream(is)) {
                        ZipEntry entry;
                        while ((entry = zis.getNextEntry()) != null) {
                                if (entry.getName().contains(".pdf")) {
                                        try (InputStream inputStream = zipfile.getInputStream(entry)) {
                                                PDF pdf = new PDF(inputStream);
                                                assertThat(pdf.author).isEqualTo("Chromium");
                                                assertThat(pdf.numberOfPages).isEqualTo(8);
                                        }
                                }
                        }
                }
        }

        @DisplayName("Checking XLS-file from ZIP")
        @Test
        void zipXlsTest() throws Exception {
                ZipFile zipfile = new ZipFile(new File("src/test/resources/Test_HW.zip"));
                try (InputStream is = cl.getResourceAsStream("Test_HW.zip");
                     ZipInputStream zis = new ZipInputStream(is)) {
                        ZipEntry entry;
                        while ((entry = zis.getNextEntry()) != null) {
                                if (entry.getName().contains(".xlsx")) {
                                        try (InputStream inputStream = zipfile.getInputStream(entry)) {
                                                XLS xls = new XLS(inputStream);
                                                AssertionsForClassTypes.assertThat(
                                                        xls.excel.getSheetAt(0)
                                                                .getRow(1)
                                                                .getCell(2).getStringCellValue()
                                                ).isEqualTo("Волгоград");
                                        }
                                }
                        }
                }
        }

        @DisplayName("Checking CSV-file from ZIP")
        @Test
        void zipCsvTest() throws Exception {
                ZipFile zipfile = new ZipFile(new File("src/test/resources/Test_HW.zip"));
                try (InputStream is = cl.getResourceAsStream("Test_HW.zip");
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
        }
