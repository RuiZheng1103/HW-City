package com.city.utils.io;

import com.city.pojo.Cities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileReaderTest {
    @DisplayName("read file")
    @Test
    public void readFile() throws IOException {

        List<Cities> expected =
                Arrays.asList(new Cities("Boston", "New York"),
                        new Cities("Trenton", "Albany"));

        IDataReader IDataReader = new FileDataReader("classpath:cityTest1.txt");
        List<Cities> cities = IDataReader.getData();
        Assertions.assertEquals(expected, cities);
    }

    @DisplayName("read not existed file")
    @Test
    public void readNonExistedFile() throws IOException{

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            IDataReader IDataReader = new FileDataReader("classpath:cityTest4.txt");
            IDataReader.getData();
        });
    }

}
