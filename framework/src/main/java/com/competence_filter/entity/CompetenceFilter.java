package com.competence_filter.entity;

import com.couchbase.framework.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * id is the searchPhrase
 */
public class CompetenceFilter extends Entity {
    public static String getId(String shopName, String searchPhrase) {
        return shopName + "::" + searchPhrase;
    }
    private String shop;
    private List<Filter> filters = new ArrayList<Filter>();

    public String getId() {
        return CompetenceFilter.getId(shop, super.getId());
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }
}
