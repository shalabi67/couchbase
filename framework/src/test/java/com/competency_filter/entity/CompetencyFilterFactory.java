package com.competency_filter.entity;

public class CompetencyFilterFactory {
    public static final String NAME = "name";
    public static final int COUNT = 5;
    public static CompetencyFilter createDefault(String shop, String searchPhrase) {
        CompetencyFilter competencyFilter = new CompetencyFilter();
        competencyFilter.setShop(shop);
        competencyFilter.setId(searchPhrase);
        for(int i=0;i<COUNT; i++) {
            competencyFilter.addFilter(createFilter(NAME + i, i));
        }

        return competencyFilter;
    }

    public static Filter  createFilter(String name, int count) {
        Filter filter = new Filter();
        filter.setCount(count);
        filter.setFilter(name);

        return filter;
    }
}
