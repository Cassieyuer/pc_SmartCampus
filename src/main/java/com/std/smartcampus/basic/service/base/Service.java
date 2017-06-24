package com.std.smartcampus.basic.service.base;

import java.io.Serializable;

import com.std.smartcampus.basic.repository.BaseRepository;

public abstract class Service<T, ID extends Serializable> {
	protected BaseRepository<T, ID> repository;
}
