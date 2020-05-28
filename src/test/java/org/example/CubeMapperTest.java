package org.example;


import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

public class CubeMapperTest {

    @Test
    public void testMapCubes() {

        //given
        CubeMapper cubeMapper = new CubeMapper();

        Element element1 = new Element("cube");
        element1.setAttribute("currency", "USD");
        element1.setAttribute("rate", "1.0975");

        Element element2 = new Element("cube");
        element2.setAttribute("currency", "JPY");
        element2.setAttribute("rate", "117.92");

        Element element3 = new Element("cube");
        element3.setAttribute("currency", "");
        element3.setAttribute("rate", "");

        //when
        Cube result1 = cubeMapper.mapCubes(element1);
        Cube result2 = cubeMapper.mapCubes(element2);
        Cube result3 = cubeMapper.mapCubes(element3);

        //then
        Assert.assertEquals(result1.getCurrency(), "USD");
        Assert.assertEquals(result2.getRate(), "117.92");
        Assert.assertEquals(result3.getCurrency(), "");

    }
}
