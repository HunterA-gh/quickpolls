package com.example.quickpoll.services;

import com.example.quickpoll.models.Poll;
import com.example.quickpoll.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public List<Poll> getAllPolls(){
        return (List<Poll>) pollRepository.findAll();
    }

    public Poll getPoll(Long pollId){
        return pollRepository.findById(pollId).orElse(null);
    }

    public void createPoll(Poll poll){
        pollRepository.save(poll);
    }

    public void updatePoll(Poll poll){
        pollRepository.save(poll);
    }

    public void deletePoll(Long pollId){
        pollRepository.deleteById(pollId);
    }
}
