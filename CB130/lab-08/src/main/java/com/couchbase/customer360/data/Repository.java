package com.couchbase.customer360.data;

import com.couchbase.customer360.domain.Entity;

/**
 * Interface that defines standard CRUD operations for entities.
 * 
 * @author Tony Piazza
 */
public interface Repository {
	/**
	 * Find an entity by the specified ID and return a reference to
	 * an instance of the specified type.
	 * 
	 * @param id Unique ID of the entity
	 * @param type Type of the entity to return
	 * @return Reference to an instance of the specified type that 
	 * 	corresponds to the ID 
	 */
	<T extends Entity> T findById(String id, Class<? extends T> type);

	/**
	 * Persist a new instance of the specified type in the repository.
	 * 
	 * @param entity Source entity to be persisted
	 * @param type The type of the entity to be persisted
	 * @return Reference to the entity that has been persisted 
	 */
	<T extends Entity> T create(T entity, Class<? extends T> type);

	/**
	 * Update an existing instance of the specified type in the repository.
	 * 
	 * @param entity Source entity to be persisted
	 * @param type The type of the entity to be persisted
	 * @return Reference to the entity that has been persisted 
	 */
	<T extends Entity> T update(T entity, Class<? extends T> type);

	/**
	 * Insert or update an instance of the specified type in the repository.
	 * 
	 * @param entity Source entity to be persisted
	 * @param type The type of the entity to be persisted
	 * @return Reference to the entity that has been persisted 
	 */
	<T extends Entity> T upsert(T entity, Class<? extends T> type);

	/**
	 * Delete the specified entity from the repository.
	 * 
	 * @param entity Source entity to be deleted
	 */
	<T extends Entity> void delete(T entity);
}