package com.example.quickpoll.repositories;

import com.example.quickpoll.models.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
}
