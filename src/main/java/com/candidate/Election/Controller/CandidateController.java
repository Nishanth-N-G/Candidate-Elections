package com.candidate.Election.Controller;

import com.candidate.Election.Model.Candidate;
import com.candidate.Election.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidateController {
    private CandidateService candidateService;

    @PostMapping("/registerCandidate")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerCandidate(@RequestParam String name){
        return candidateService.registerCandidate(name);
    }

    @PutMapping("/castVote")
    @ResponseStatus(HttpStatus.OK)
    public String castMyVote(@RequestParam String name){
        return candidateService.castMyVote(name);
    }

    @GetMapping("/countCandidateVote")
    @ResponseStatus(HttpStatus.OK)
    public String countCandidateVote(@RequestParam String name){
        return candidateService.countCandidateVote(name);
    }

    @GetMapping("/listVotes")
    @ResponseStatus(HttpStatus.OK)
    public List<Candidate> countAllVotes(){
        return candidateService.countAllVotes();
    }

    @GetMapping("/getWinner")
    @ResponseStatus(HttpStatus.OK)
    public String announceWinner(){
        return candidateService.announceWinner();
    }
}
