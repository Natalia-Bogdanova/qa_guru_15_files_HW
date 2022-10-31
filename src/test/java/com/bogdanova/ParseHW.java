package com.bogdanova;

import org.junit.jupiter.api.Test;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ParseHW {
        ClassLoader cl = ParseHW.class.getClassLoader();

        @Test


        void zipXls() throws Exception {
                InputStream is = cl.getResourceAsStream("TestHW.zip");
                ZipInputStream zis = new ZipInputStream(new ZipInputStream(is));
                ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();
                long size = entry.getSize();
        System.out.printf("File name: %s \t File size: %d \n", name, size);
        assertThat(entry.getName()).isEqualTo("TestHW/Клиники по программе.xlsx");
        assertThat(entry.getSize()).isEqualTo(12288);
        }
        }

        @Test
        void zipCsv() throws Exception {
        InputStream is = cl.getResourceAsStream("TestHW.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
        String name = entry.getName();
        long size = entry.getSize();
        System.out.printf("File name: %s \t File size: %d \n", name, size);
        assertThat(entry.getName()).isEqualTo("fileCSV.csv");
        assertThat(entry.getSize()).isEqualTo(68);
        }
        }

        @Test
        void zipPdf() throws Exception {
        InputStream is = cl.getResourceAsStream("TestHW.zip");
        ZipInputStream zis = new ZipInputStream(new ZipInputStream(is));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
        String namePdf = entry.getName();
        long size = entry.getSize();
        assertThat(entry.getName()).isIn("Natalia Bogdanova Communication Profile.pdf");
        System.out.println(entry.getName());
        }
        }


        }