package com.competence_filter.entity;

public class CompetencyFilterFactory {
    public static final String NAME = "name";
    public static final int COUNT = 5;
    public static CompetenceFilter createDefault(String shop, String searchPhrase) {
        CompetenceFilter competenceFilter = new CompetenceFilter();
        competenceFilter.setShop(shop);
        competenceFilter.setId(searchPhrase);
        for(int i=0;i<COUNT; i++) {
            competenceFilter.addFilter(createFilter(NAME + i, i));
        }

        return competenceFilter;
    }

    public static Filter createFilter(String name, int count) {
        Filter filter = new Filter();
        filter.setCount(count);
        filter.setFilter(name);

        return filter;
    }
}
