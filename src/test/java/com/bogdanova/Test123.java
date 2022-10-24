package com.bogdanova;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.*;
import java.util.zip.*;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Test123 {

    ClassLoader cl = Test123.class.getClassLoader();

    @Test
    void zipTest() throws IOException {
        InputStream is = cl.getResourceAsStream("folder/TestHW.zip");
        ZipInputStream zis = new ZipInputStream(is);
         {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                assertThat(entry.getName())
                        .isIn("fileCSV.csv")
                        .isIn("Natalia Bogdanova Communication Profile.pdf")
                        .isIn("Клиники по программе.xlsx");
            }
        }
    }
}
