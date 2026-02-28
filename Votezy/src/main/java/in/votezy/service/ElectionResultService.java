package in.votezy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.ElectionResult;
import in.votezy.exception.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.ElectionResultRepository;
import in.votezy.repository.VoteRepository;

@Service
public class ElectionResultService {

	private ElectionResultRepository electionResultRepository;
	private CandidateRepository candidateRepository;
	private VoteRepository voteRepository;

	@Autowired
	public ElectionResultService(ElectionResultRepository electionResultRepository,
			CandidateRepository candidateRepository, VoteRepository voteRepository) {
		this.electionResultRepository = electionResultRepository;
		this.candidateRepository = candidateRepository;
		this.voteRepository = voteRepository;
	}
	
	public ElectionResult declareResult(String electionName) {
		Optional<ElectionResult> existingResult = electionResultRepository.findByElectionName(electionName);
		if(existingResult.isPresent()) {
			return existingResult.get();
		}
		if(voteRepository.count()==0) {
			throw new IllegalStateException("Cannot declare Result as no vote have been casted");
		}
		List<Candidate> allCandidates = candidateRepository.findAllByOrderByVoteCountDesc();
		if(allCandidates.isEmpty()) {
			throw new ResourceNotFoundException("No Candidates available");
		}
		Candidate winner = allCandidates.get(0);
		int totalVotes = 0;
		for(Candidate candidate : allCandidates) {
			totalVotes +=candidate.getVoteCount();
		}
		ElectionResult electionResult = new ElectionResult();
		electionResult.setElectionName(electionName);
		electionResult.setTotalVotes(totalVotes);
		electionResult.setWinner(winner);
		return electionResultRepository.save(electionResult);
	}
	
	public List<ElectionResult> getAllElectionResults(){
		return electionResultRepository.findAll();
	}

}
