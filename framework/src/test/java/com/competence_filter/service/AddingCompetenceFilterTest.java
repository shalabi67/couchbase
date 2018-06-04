package com.competence_filter.service;

import com.competence_filter.entity.CompetenceFilter;
import com.competence_filter.entity.CompetencyFilterFactory;
import com.competence_filter.service.data.CompetenceFilterDataService;
import com.competence_filter.service.data.CompetenceFilterDatabase;
import org.junit.Assert;
import org.junit.Test;

public class AddingCompetenceFilterTest {
    boolean success = false;
    @Test
    public void addNewCompetenceFilter() {

        CompetenceFilterDatabase competenceFilterDatabase = new CompetenceFilterDatabase();
        competenceFilterDatabase.add(CompetencyFilterFactory.createDefault("shop", "testsearch"))
        .subscribe((competencyFilter) -> {

        }, (exception) -> {
            System.out.println("failed to add record.");
            success = false;
        }, () -> success = true);

        Assert.assertTrue(success);
    }

    @Test
    public void getCompetenceFilterUsingDataService() {
        CompetenceFilterDataService competenceFilterDataService = new CompetenceFilterDataService();
        CompetenceFilter competenceFilter = competenceFilterDataService.get("shop", "testsearch");
    }
}
