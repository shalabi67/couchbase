package com.competency_filter.service;

import com.competency_filter.entity.CompetencyFilter;
import com.competency_filter.entity.CompetencyFilterFactory;
import com.couchbase.client.core.message.observe.Observe;
import io.reactivex.disposables.CompositeDisposable;
import org.junit.Assert;
import org.junit.Test;

public class AddingCompetencyFilterTest {
    boolean success = false;
    @Test
    public void addNewCompetencyFilter() {

        CompetencyFilterDatabase competencyFilterDatabase = new CompetencyFilterDatabase();
        competencyFilterDatabase.add(CompetencyFilterFactory.createDefault("shop", "testsearch"))
        .subscribe((competencyFilter) -> {

        }, (exception) -> {
            System.out.println("failed to add record.");
            success = false;
        }, () -> success = true);

        Assert.assertTrue(success);
    }
}
