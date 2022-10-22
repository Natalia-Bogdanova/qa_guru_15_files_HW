package com.bogdanova;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseHW {
    ClassLoader cl = ParseHW.class.getClassLoader();

    @Test
    void zipXls() throws Exception {
        InputStream is = cl.getResourceAsStream("TestHW.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String name = entry.getName();
            long size = entry.getSize();
            System.out.printf("File name: %s \t File size: %d \n", name, size);
            assertThat(entry.getName()).isEqualTo("Клиники по программе.xlsm");
            assertThat(entry.getSize()).isEqualTo(68);
        }
    }

    @Test
    void zipCsv() throws Exception {
        InputStream is = cl.getResourceAsStream("folder/sample4.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String name = entry.getName();
            long size = entry.getSize();
            System.out.printf("File name: %s \t File size: %d \n", name, size);
            assertThat(entry.getName()).isEqualTo("sample4.csv");
            assertThat(entry.getSize()).isEqualTo(7918);
        }
    }

    @Test
    void zipPdf() throws Exception {
        InputStream is = cl.getResourceAsStream("folder/PDF_829613669_10.2022_15.19.43.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String namePdf = entry.getName();
            long size = entry.getSize();
            assertThat(entry.getName()).isEqualTo("PDF_829613669_10.2022_15.19.43.pdf");
            System.out.println(entry.getName());
        }
    }

}