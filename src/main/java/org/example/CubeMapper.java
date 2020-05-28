package org.example;

import org.jdom2.Element;

import java.util.Optional;

public class CubeMapper {

    public Cube mapCubes(Element cube) {

        Optional<Element> cubeElementOptional = Optional.ofNullable(cube);
        Cube singleCube = new Cube();

        String currency = cubeElementOptional
                .map(element -> element.getAttributeValue("currency"))
                .orElse(null);

        String rate = cubeElementOptional
                .map(element -> element.getAttributeValue("rate"))
                .orElse(null);

        singleCube.setCurrency(currency);
        singleCube.setRate(rate);

        return singleCube;
    }
}
