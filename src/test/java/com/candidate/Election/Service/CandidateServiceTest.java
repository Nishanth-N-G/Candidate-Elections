package com.candidate.Election.Service;

import com.candidate.Election.Model.Candidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CandidateServiceTest {

    private CandidateService candidateService;

    @BeforeEach
    public void setUp() {
        candidateService = new CandidateService();
    }

    @Test
    @DisplayName("Testing Candidate Registration")
    public void testRegisterCandidate() {
        assertEquals("Candidate1 you are Registered for Election. All the best.",
                candidateService.registerCandidate("Candidate1"));
        assertEquals("Candidate1 you are already registered.",
                candidateService.registerCandidate("Candidate1"));

    }

    @Test
    @DisplayName("Testing Candidate Voting")
    public void testCastMyVote() {
        candidateService.registerCandidate("Candidate2");
        assertEquals("You have voted for Candidate2", candidateService.castMyVote("Candidate2"));
        assertEquals("Sorry! We didn't find your Candidate.", candidateService.castMyVote("Candidate20"));
    }

    @Test
    @DisplayName("Counting Candidate Votes")
    public void testCountCandidateVote() {
        candidateService.registerCandidate("Candidate3");
        candidateService.castMyVote("Candidate3");
        assertEquals("Candidate3 have 1 votes.", candidateService.countCandidateVote("Candidate3"));
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
        assertEquals("All Candidates are with 0 votes, No Winner yet.", candidateService.announceWinner());
        candidateService.castMyVote("Candidate6");
        candidateService.castMyVote("Candidate6");
        candidateService.registerCandidate("Candidate7");
        candidateService.castMyVote("Candidate7");
        assertEquals("Winner of this election is Candidate6 with 2 votes!! Congratulation.",
                candidateService.announceWinner());
        candidateService.castMyVote("Candidate7");
        assertEquals("The Election is tied between Candidate6 Candidate7 ", candidateService.announceWinner());

    }
}