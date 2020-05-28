package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DataParserTest {

    @Test
    public void parseDataTest() {

        //given
        DataParser dataParser = new DataParser();

        //when
        List<Cube> cubeList = dataParser.parseData();

        //then
        Assert.assertEquals(cubeList.get(0).getCurrency(), "USD");
        Assert.assertEquals(cubeList.get(1).getRate(), "117.92");
        Assert.assertEquals(cubeList.get(2).getCurrency(), "BGN");
        Assert.assertEquals(cubeList.size(), 32);

    }
}
