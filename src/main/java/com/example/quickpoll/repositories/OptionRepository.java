package com.example.quickpoll.repositories;

import com.example.quickpoll.models.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
}
