package org.example;

import org.jdom2.Element;

import java.math.BigDecimal;
import java.util.Optional;

public class CubeMapper {

    public Cube mapCubes(Element cube) {

        Optional<Element> cubeElementOptional = Optional.ofNullable(cube);
        Cube singleCube = new Cube();

        String currency = cubeElementOptional
                .map(element -> element.getAttributeValue("currency"))
                .orElse(null);

        BigDecimal rate = cubeElementOptional
                .map(element -> element.getAttributeValue("rate"))
                .map(BigDecimal::new)
                .orElse(null);

        singleCube.setCurrency(currency);
        singleCube.setRate(rate);

        return singleCube;
    }
}
