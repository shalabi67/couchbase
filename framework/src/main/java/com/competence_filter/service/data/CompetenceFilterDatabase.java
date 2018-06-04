package com.competence_filter.service.data;

import com.competence_filter.entity.CompetenceFilter;
import com.competence_filter.repository.CompetenceFilterRepository;
import io.reactivex.Observable;

public class CompetenceFilterDatabase {
    private CompetenceFilterRepository competenceFilterRepository = new CompetenceFilterRepository(new DatabaseConfiguration().clustersNames,
            new DatabaseConfiguration().bucketName, new DatabaseConfiguration().bucketPassword);
    public Observable<CompetenceFilter> add(final CompetenceFilter competenceFilter) {
        Observable<CompetenceFilter> observable = Observable.create(emitter -> {
            CompetenceFilter newCompetenceFilter =  competenceFilterRepository.insert(competenceFilter);

            emitter.onNext(newCompetenceFilter);
            emitter.onComplete();

        });

        return observable;
    }

    public CompetenceFilter getCompetencyFilter(final String competencyFilterId) {
        return competenceFilterRepository.findById(competencyFilterId);
    }
}
