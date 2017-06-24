/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/** 
 * @author: 李志鹏
 * @date: 2017年5月15日 下午11:33:46 
 */
public abstract class BaseService<T, ID extends Serializable> extends JpaService<T, ID> {
	/**
	 * Returns a single entity matching the given {@link Example} or {@literal null} if none was found.
	 *
	 * @param example can be {@literal null}.
	 * @return a single entity matching the given {@link Example} or {@literal null} if none was found.
	 * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if the Example yields more than one result.
	 */
	public <S extends T> S findOne(Example<S> example){
		return repository.findOne(example);
	}

	/**
	 * Returns all entities matching the given {@link Example}. In case no match could be found an empty {@link Iterable}
	 * is returned.
	 *
	 * @param example can be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 */
	public <S extends T> Iterable<S> findAll(Example<S> example){
		return repository.findAll(example);
	}

	/**
	 * Returns all entities matching the given {@link Example} applying the given {@link Sort}. In case no match could be
	 * found an empty {@link Iterable} is returned.
	 *
	 * @param example can be {@literal null}.
	 * @param sort the {@link Sort} specification to sort the results by, must not be {@literal null}.
	 * @return all entities matching the given {@link Example}.
	 * @since 1.10
	 */
	public <S extends T> Iterable<S> findAll(Example<S> example, Sort sort){
		return repository.findAll(example, sort);
	}

	/**
	 * Returns a {@link Page} of entities matching the given {@link Example}. In case no match could be found, an empty
	 * {@link Page} is returned.
	 *
	 * @param example can be {@literal null}.
	 * @param pageable can be {@literal null}.
	 * @return a {@link Page} of entities matching the given {@link Example}.
	 */
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable){
		return repository.findAll(example, pageable);
	}

	/**
	 * Returns the number of instances matching the given {@link Example}.
	 *
	 * @param example the {@link Example} to count instances for, can be {@literal null}.
	 * @return the number of instances matching the {@link Example}.
	 */
	public <S extends T> long count(Example<S> example){
		return repository.count(example);
	}

	/**
	 * Checks whether the data store contains elements that match the given {@link Example}.
	 *
	 * @param example the {@link Example} to use for the existence check, can be {@literal null}.
	 * @return {@literal true} if the data store contains elements that match the given {@link Example}.
	 */
	public <S extends T> boolean exists(Example<S> example){
		return repository.exists(example);
	}
	
	//////////////////////////////黄金分割线///////////////////////////////////////
	
	/**
	 * Returns a single entity matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public T findOne(Specification<T> spec){
		return repository.findOne(spec);
	}

	/**
	 * Returns all entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @return
	 */
	public List<T> findAll(Specification<T> spec){
		return repository.findAll(spec);
	}

	/**
	 * Returns a {@link Page} of entities matching the given {@link Specification}.
	 * 
	 * @param spec
	 * @param pageable
	 * @return
	 */
	public Page<T> findAll(Specification<T> spec, Pageable pageable){
		return repository.findAll(spec, pageable);
	}

	/**
	 * Returns all entities matching the given {@link Specification} and {@link Sort}.
	 * 
	 * @param spec
	 * @param sort
	 * @return
	 */
	public List<T> findAll(Specification<T> spec, Sort sort){
		return repository.findAll(spec, sort);
	}

	/**
	 * Returns the number of instances that the given {@link Specification} will return.
	 * 
	 * @param spec the {@link Specification} to count instances for
	 * @return the number of instances
	 */
	public long count(Specification<T> spec){
		return repository.count(spec);
	}
}
