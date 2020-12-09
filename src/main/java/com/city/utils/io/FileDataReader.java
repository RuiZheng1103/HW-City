package com.city.utils.io;

import com.city.pojo.Cities;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataReader implements IDataReader {

    private String fileLocation;

    public FileDataReader(String fileLocation){
        this.fileLocation = fileLocation;
    }

    @Override
    public List<Cities> getData() throws IOException{
        List<Cities> citiesList = new ArrayList<>();

        try {
            File target = ResourceUtils.getFile(this.fileLocation);
            FileReader fileReader = new FileReader(target);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine = null;
            while ((currentLine = bufferedReader.readLine()) != null){
                String[] arr = currentLine.split(",");
                citiesList.add(new Cities(arr[0].trim(), arr[1].trim()));
            }

            bufferedReader.close();
            fileReader.close();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e){
            throw new IOException();
        }

        return citiesList;

    }


}
