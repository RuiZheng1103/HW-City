package com.city.utils.io;

import com.city.pojo.Cities;

import java.io.IOException;
import java.util.List;

public interface IDataReader {
    public List<Cities> getData() throws IOException;
}
