package com.candidate.Election.Service;

import com.candidate.Election.Model.Candidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    List<Candidate> electionCandidates=new ArrayList<>();

    public synchronized String registerCandidate(String name) {
        if(name.isEmpty()) return "You need to enter your name for Registration.";
        Optional<Candidate> candidate= electionCandidates.stream().filter(c->c.getName().equals(name)).findAny();
        if(candidate.isPresent()) {
            return name+" you are already registered.";
        }
        electionCandidates.add(new Candidate(name, 0));
        return name+" you are Registered for Election. All the best.";
    }

    public synchronized String castMyVote(String name) {
        Optional<Candidate> candidate = electionCandidates.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();

        if(candidate.isPresent()) {
            Candidate foundCandidate = candidate.get();
            foundCandidate.setVotes(foundCandidate.getVotes()+1);
            return "You have voted for "+foundCandidate.getName();
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
            return  foundCandidate.getName()+" have " +foundCandidate.getVotes()+ " votes.";
        }else{
            return "Sorry! We didn't find your Candidate to check votes.";
        }
    }

    public synchronized List<Candidate> countAllVotes() {
        return electionCandidates;
    }

    public synchronized String announceWinner() {
        Optional<Candidate> winner = electionCandidates.stream().max(Comparator.comparingInt(Candidate::getVotes));
        if(winner.isPresent()) {
            Candidate win = winner.get();
            int maxVotes = win.getVotes();
            if(maxVotes==0){
                return "All Candidates are with 0 votes, No Winner yet.";
            }
            List<String> tieResult=new ArrayList<>();
            for(Candidate candidate: electionCandidates) {
                if (candidate.getVotes() == maxVotes) {
                    tieResult.add(candidate.getName());
                }
            }
            if(tieResult.size()>1){
                String tieNamesList="";
                for(String name:tieResult){
                    tieNamesList= tieNamesList.concat(name+" ");
                }
                return "The Election is tied between "+tieNamesList;
            }
            return "Winner of this election is " + win.getName() + " with " + win.getVotes() + " votes!! Congratulation.";
        }else{
            return "No Winner yet.";
        }

    }
}
