/*
 * Copyright 2008-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.std.smartcampus.basic.service.base;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

/**
 * JPA specific extension of {@link org.springframework.data.repository.Repository}.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public abstract class JpaService<T, ID extends Serializable>
		extends PagingAndSortingService<T, ID> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudService#findAll()
	 */
	public List<T> findAll(){
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.PagingAndSortingService#findAll(org.springframework.data.domain.Sort)
	 */
	public List<T> findAll(Sort sort){
		return repository.findAll(sort);
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudService#findAll(java.lang.Iterable)
	 */
	public List<T> findAll(Iterable<ID> ids){
		return repository.findAll(ids);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudService#save(java.lang.Iterable)
	 */
	public <S extends T> List<S> save(Iterable<S> entities){
		return repository.save(entities);
		
	}

	/**
	 * Flushes all pending changes to the database.
	 */
	public void flush(){
		repository.flush();
	}

	/**
	 * Saves an entity and flushes changes instantly.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	public <S extends T> S saveAndFlush(S entity){
		return repository.saveAndFlush(entity);
		
	}

	/**
	 * Deletes the given entities in a batch which means it will create a single {@link Query}. Assume that we will clear
	 * the {@link javax.persistence.EntityManager} after the call.
	 * 
	 * @param entities
	 */
	public void deleteInBatch(Iterable<T> entities){
		repository.deleteInBatch(entities);
	}

	/**
	 * Deletes all entities in a batch call.
	 */
	public void deleteAllInBatch(){
		repository.deleteAllInBatch();
	}

	/**
	 * Returns a reference to the entity with the given identifier.
	 * 
	 * @param id must not be {@literal null}.
	 * @return a reference to the entity with the given identifier.
	 * @see EntityManager#getReference(Class, Object)
	 */
	public T getOne(ID id){
		return repository.getOne(id);
	}


}
