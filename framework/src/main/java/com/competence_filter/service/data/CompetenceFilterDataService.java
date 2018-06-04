package com.competence_filter.service.data;

import com.competence_filter.entity.CompetenceFilter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompetenceFilterDataService {

    public void add(CompetenceFilter competenceFilter) {
        CompetenceFilterDatabase competenceFilterDatabase = new CompetenceFilterDatabase();
        competenceFilterDatabase.add(competenceFilter)
                .subscribeOn(Schedulers.io())
                .subscribe(emptyObserver);
    }
    public void add(CompetenceFilter competenceFilter, Observer<CompetenceFilter> observer) {
        CompetenceFilterDatabase competenceFilterDatabase = new CompetenceFilterDatabase();
        competenceFilterDatabase.add(competenceFilter)
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public CompetenceFilter get(String shopName, String searchPhrase) {
        CompetenceFilterDatabase competenceFilterDatabase = new CompetenceFilterDatabase();
        String id = CompetenceFilter.getId(shopName, searchPhrase);
        return competenceFilterDatabase.getCompetencyFilter(id);
    }

    private Observer<CompetenceFilter> emptyObserver = new Observer<CompetenceFilter>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(CompetenceFilter competenceFilter) {

        }

        @Override
        public void onError(Throwable e) {
            //TODO: how to handle errors here
        }

        @Override
        public void onComplete() {

        }
    };
}
