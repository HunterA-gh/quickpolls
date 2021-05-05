package com.example.quickpoll.controllers;

import com.example.quickpoll.handling.ResourceNotFoundException;
import com.example.quickpoll.models.Poll;
import com.example.quickpoll.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping("/polls")
    public ResponseEntity<Poll> getAllPolls() {
        return new ResponseEntity(pollService.getAllPolls(), HttpStatus.OK);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        return new ResponseEntity<>(pollService.getPoll(pollId), HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        pollService.createPoll(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId()).toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
        verifyPoll(pollId);
        pollService.updatePoll(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Poll p = pollService.getPoll(pollId);
        if (p == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
}
