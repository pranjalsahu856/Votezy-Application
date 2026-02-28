package in.votezy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.entity.Voter;
import in.votezy.exception.ResourceNotFoundException;
import in.votezy.exception.VoteNotAllowedException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoteRepository;
import in.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VotingService {
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	private VoterRepository voterRepository;
	
	public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}
	
	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		if(!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter with id : "+voterId+" not Found");
		}
		if(!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate with id : "+candidateId+" not Found");
		}
		Voter voter = voterRepository.findById(voterId).get();
		if(voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter Id: "+voterId+" has already casted");
		}
		Candidate candidate =candidateRepository.findById(candidateId).get();
		
		Vote vote = new Vote();
		vote.setCandidate(candidate);
		vote.setVoter(voter);
		
		candidate.setVoteCount(candidate.getVoteCount()+1);
		candidateRepository.save(candidate);
		
		voter.setVote(vote);
		voter.setHasVoted(true);
		voterRepository.save(voter);
		return vote;
	}
	
	public List<Vote> getAllVotes(){
		return voteRepository.findAll();
	}
}
