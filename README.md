Election API Documentation
--------------------------

Introduction:
This API is designed for conducting elections and managing candidate registrations, votes, and election results.

Endpoints:
1. Register Candidate:
    Endpoint: http://localhost:8080/registerCandidate
    Usage: To register a candidate for the election.
    Method: POST
    Query Parameter: name (e.g., http://localhost:8080/registerCandidate?name=Candidate1)

2. Cast Vote:
    Endpoint: http://localhost:8080/castVote
    Usage: To cast a vote for a specific candidate.
    Method: PUT
    Query Parameter: name (e.g., http://localhost:8080/castVote?name=Candidate1)

3. Count Candidate Vote:
    Endpoint: http://localhost:8080/countCandidateVote
    Usage: To check the vote count of a particular candidate.
    Method: GET
    Query Parameter: name (e.g., http://localhost:8080/countCandidateVote?name=Candidate1)

4. List Votes:
    Endpoint: http://localhost:8080/listVotes
    Usage: To retrieve a list of candidates with their respective vote counts.
    Method: GET

5. Get Winner:
    Endpoint: http://localhost:8080/getWinner
    Usage: To get the winner of the election with the maximum number of votes.
    Method: GET

Usage:
- Use the provided endpoints with the specified query parameters for various election-related actions.
- Ensure proper URL encoding of query parameters.

Note:
- Replace 'Candidate1' in the query parameters with the actual candidate name while using the APIs.
- Handle responses and errors according to the provided status codes and response formats.

Sample Usage:
Example usage of endpoints:
1. Register Candidate: http://localhost:8080/registerCandidate?name=Nishanth
2. Cast Vote: http://localhost:8080/castVote?name=Nishanth
3. Count Candidate Vote: http://localhost:8080/countCandidateVote?name=Nishanth
4. List Votes: http://localhost:8080/listVotes
5. Get Winner: http://localhost:8080/getWinner

Contact:
For inquiries or assistance, please contact Nishanth at nishanthsomu64@gmail.com.
