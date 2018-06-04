package com.competency_filter.service;

import com.competency_filter.entity.CompetencyFilter;
import com.competency_filter.repository.CompetencyFilterRepository;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class CompetencyFilterDatabase {
    private CompetencyFilterRepository competencyFilterRepository = new CompetencyFilterRepository(new DatabaseConfiguration().clustersNames,
            new DatabaseConfiguration().bucketName, new DatabaseConfiguration().bucketPassword);
    public Observable<CompetencyFilter> add(final CompetencyFilter competencyFilter) {
        Observable<CompetencyFilter> observable = Observable.create(new ObservableOnSubscribe<CompetencyFilter>() {
            public void subscribe(ObservableEmitter<CompetencyFilter> emitter) throws Exception {
                CompetencyFilter newCompetencyFilter =  competencyFilterRepository.insert(competencyFilter);

                emitter.onNext(newCompetencyFilter);
                emitter.onComplete();

            }
        });

        return observable;
    }

    public Observable<CompetencyFilter> getCompetencyFilter(final String competencyFilterId) {
        Observable<CompetencyFilter> observable = Observable.create(new ObservableOnSubscribe<CompetencyFilter>() {
            public void subscribe(ObservableEmitter<CompetencyFilter> emitter) throws Exception {
                CompetencyFilter competencyFilter = competencyFilterRepository.findById(competencyFilterId);
                emitter.onNext(competencyFilter);
                emitter.onComplete();

            }
        });

        return observable;
    }
}
