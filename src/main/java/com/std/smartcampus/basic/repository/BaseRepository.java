/**   
 * Copyright © 2017 公司名. All rights reserved.
 */
package com.std.smartcampus.basic.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


/** 
 * @author: 李志鹏
 * @date: 2017年5月15日 下午4:37:03 
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>{

}
