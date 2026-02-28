package in.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.votezy.entity.Candidate;
import in.votezy.entity.Vote;
import in.votezy.entity.Voter;
import in.votezy.exception.DuplicateResourceException;
import in.votezy.exception.ResourceNotFoundException;
import in.votezy.repository.CandidateRepository;
import in.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VoterService {
	private VoterRepository voterRepository;
	private CandidateRepository candidateRepository;
	@Autowired
	public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
		this.voterRepository = voterRepository;
		this.candidateRepository = candidateRepository;
	}
	
	public Voter registerVoter(Voter voter) {
		if(voterRepository.existsByEmail(voter.getEmail())) {
			throw new DuplicateResourceException("Voter with Email Id : "+voter.getEmail()+" Already Exists");
		}
		return voterRepository.save(voter);
	}
	
	public List<Voter> getAllVoters(){
		return voterRepository.findAll();
	}
	
	public Voter getVoterById(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter!=null) {
			return voter;
		}else {
			throw new ResourceNotFoundException("Voter with Id : "+id+" not Found");
		}
	}
	
	public Voter updateVoter(Long id,Voter updatedVoter) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Voter with Id : "+id+" not Found");
		}
		if(updatedVoter.getEmail()!=null) {
			voter.setEmail(updatedVoter.getEmail());
		}
		if(updatedVoter.getName()!=null) {
			voter.setName(updatedVoter.getName());
		}
		return voterRepository.save(voter);
	}
	
	@Transactional
	public void deleteVoter(Long id) {
		Voter voter = voterRepository.findById(id).orElse(null);
		if(voter==null) {
			throw new ResourceNotFoundException("Connot Delete Voter with id : "+id+" as it does not exists");
		}
		Vote vote = voter.getVote();
		if(vote!=null) {
			Candidate candidate = vote.getCandidate();
			candidate.setVoteCount(candidate.getVoteCount()-1);
			candidateRepository.save(candidate);
		}
		voterRepository.delete(voter);
	}

}
