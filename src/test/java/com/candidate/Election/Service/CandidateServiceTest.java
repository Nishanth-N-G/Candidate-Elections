package com.candidate.Election.Service;

import com.candidate.Election.Model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CandidateServiceTest {

    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        candidateService = new CandidateService();
    }

    @Test
    @DisplayName("Testing Candidate Registration")
    public void testRegisterCandidate() {
        candidateService.registerCandidate("Candidate1");
        List<Candidate> candidates = candidateService.countAllVotes();
        assertTrue(candidates.stream().anyMatch(c -> c.getName().equals("Candidate1")));
    }

    @Test
    @DisplayName("Testing Candidate Voting")
    public void testCastMyVote() {
        candidateService.registerCandidate("Candidate2");
        assertEquals("You have voted for your Candidate.", candidateService.castMyVote("Candidate2"));
    }

    @Test
    @DisplayName("Counting Candidate Votes")
    public void testCountCandidateVote() {
        candidateService.registerCandidate("Candidate3");
        candidateService.castMyVote("Candidate3");
        assertEquals("You have 1 votes.", candidateService.countCandidateVote("Candidate3"));
        assertEquals("Sorry! We didn't find your Candidate to check votes.",candidateService.countCandidateVote("Candidate5"));
    }

    @Test
    @DisplayName("Testing the list of Candidates and Votes")
    public void testCountAllVotes(){
        List<Candidate> expectedCandidateList= new ArrayList<>();
        Candidate candidate4 = new Candidate("Candidate4",2);
        Candidate candidate5 = new Candidate("Candidate5",1);
        expectedCandidateList.add(candidate4);
        expectedCandidateList.add(candidate5);
        candidateService.registerCandidate("Candidate4");
        candidateService.registerCandidate("Candidate5");
        candidateService.castMyVote("Candidate4");
        candidateService.castMyVote("Candidate4");
        candidateService.castMyVote("Candidate5");
        List<Candidate> actualCandidateList= candidateService.countAllVotes();
        assertEquals(expectedCandidateList.get(0).getName(),actualCandidateList.get(0).getName());
        assertEquals(expectedCandidateList.get(1).getName(),actualCandidateList.get(1).getName());
        assertEquals(expectedCandidateList.get(0).getVotes(),actualCandidateList.get(0).getVotes());
        assertEquals(expectedCandidateList.get(1).getVotes(),actualCandidateList.get(1).getVotes());
    }

    @Test
    @DisplayName("Testing the Winner in Election")
    public void testAnnounceWinner() {
        candidateService.registerCandidate("Candidate6");
        assertEquals("No Winner yet.", candidateService.announceWinner());
        candidateService.castMyVote("Candidate6");
        candidateService.castMyVote("Candidate6");
        assertEquals("Winner of this election is Candidate6 with 2 votes!! Congratulation.", candidateService.announceWinner());
    }
}