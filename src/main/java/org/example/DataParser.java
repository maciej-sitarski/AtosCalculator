package org.example;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataParser {

    public List<Cube> parseData() {

        CubeMapper cubeMapper = new CubeMapper();
        List<Cube> resultCubeList = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("data.xml");

        try {
            Document document = builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List<Element> subNotes = rootNode.getChildren();
            Element cubeFirstLevel = getCubeFirstLevel(subNotes);
            Element cubeSecondLevel = getCubeSecondLevel(cubeFirstLevel);
            List<Element> cubeList = getCubeList(cubeSecondLevel);
            resultCubeList = cubeList.stream().map(cubeMapper::mapCubes).collect(Collectors.toList());

        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
        return resultCubeList;
    }

    private Element getCubeFirstLevel(List<Element> subNotes) {
        Element cubeFirstLevel = subNotes
                .stream()
                .filter(element -> element.getName().equals("Cube"))
                .findFirst()
                .orElse(null);
        return cubeFirstLevel;
    }

    private Element getCubeSecondLevel(Element cubeFirstLevel) {
        Element cubeSecondLevel = Optional.ofNullable(cubeFirstLevel)
                .map(Element::getChildren)
                .orElseGet(Collections::emptyList)
                .stream()
                .findFirst()
                .orElse(null);
        return cubeSecondLevel;
    }

    private List<Element> getCubeList(Element cubeSecondLevel) {
        List<Element> cubeList = Optional.ofNullable(cubeSecondLevel)
                .map(Element::getChildren)
                .orElseGet(Collections::emptyList);
        return cubeList;
    }
}
