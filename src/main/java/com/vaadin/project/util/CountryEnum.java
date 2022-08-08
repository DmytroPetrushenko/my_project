package com.vaadin.project.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CountryEnum {
    USA ("USA"),
    Canada ("Canada"),
    Mexico ("Mexico"),
    GreatBritain ("Great Britain");

    private String title;

    CountryEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static List<String> valuesSting() {
        return Arrays.stream(CountryEnum.values())
                .map(CountryEnum::getTitle)
                .collect(Collectors.toList());
    }
}
