package com.candidate.Election.Service;

import com.candidate.Election.Model.Candidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    List<Candidate> electionCandidates=new ArrayList<>();

    public synchronized String registerCandidate(String name) {
        electionCandidates.add(new Candidate( name, 0));
        return "You are Registered for Election. All the best.";
    }

    public synchronized String castMyVote(String name) {
        Optional<Candidate> candidate = electionCandidates.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();

        if(candidate.isPresent()) {
            Candidate foundCandidate = candidate.get();
            foundCandidate.setVotes(foundCandidate.getVotes()+1);
            return "You have voted for your Candidate.";
        }else{
            return "Sorry! We didn't find your Candidate.";
        }
    }

    public synchronized String countCandidateVote(String name) {
        Optional<Candidate> candidate = electionCandidates.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();

        if(candidate.isPresent()) {
            Candidate foundCandidate = candidate.get();
            int votes= foundCandidate.getVotes();
            return "You have " +votes+ " votes.";
        }else{
            return "Sorry! We didn't find your Candidate to check votes.";
        }
    }

    public synchronized List<Candidate> countAllVotes() {
        return electionCandidates;
    }

    public synchronized String announceWinner() {
        Optional<Candidate> winner = electionCandidates.stream().max((c1, c2) -> Integer.compare(c1.getVotes(), c2.getVotes()));
        if(winner.isPresent()) {
            Candidate win = winner.get();
            if(win.getVotes()==0){
                return "No Winner yet.";
            }else {
                return "Winner of this election is " + win.getName() + " with " + win.getVotes() + " votes!! Congratulation.";
            }
        }else{
            return "No Winner yet.";
        }

    }
}
