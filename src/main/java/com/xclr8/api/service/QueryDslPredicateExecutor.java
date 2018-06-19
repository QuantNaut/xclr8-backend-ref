package com.xclr8.api.service;

import com.xclr8.api.domain.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;
import javax.sql.rowset.Predicate;;

/**
 * Created by shenju on 6/3/17.
 */
public interface QueryDslPredicateExecutor<T> {

   /* User findOne(Predicate predicate);

    List<User> findAll(Predicate predicate);

    Page<User> findAll(Predicate predicate, Pageable pageable);

    Long count(Predicate predicate);*/
}
