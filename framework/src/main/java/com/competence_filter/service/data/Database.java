package com.competence_filter.service.data;

import com.competence_filter.entity.CompetenceFilter;
import com.competence_filter.repository.CompetenceFilterRepository;
import com.couchbase.framework.entity.Entity;
import com.couchbase.framework.repository.CouchbaseRepository;
import io.reactivex.Observable;

public class Database<T extends Entity> {
    public <R extends CouchbaseRepository<T>> Observable<T> add(final T entity, R repository) {
        Observable<T> observable = Observable.create(emitter -> {
            T newEntity =  repository.insert(entity);

            emitter.onNext(newEntity);
            emitter.onComplete();

        });

        return observable;
    }

    public <R extends CouchbaseRepository<T>> T get(final String entityId, R repository) {
        return repository.findById(entityId);
    }
}
