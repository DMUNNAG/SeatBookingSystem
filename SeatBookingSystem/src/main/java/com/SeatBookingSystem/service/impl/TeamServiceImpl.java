package com.SeatBookingSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeatBookingSystem.entity.Team;
import com.SeatBookingSystem.exception.TeamNotFoundException;
import com.SeatBookingSystem.repository.TeamRepository;
import com.SeatBookingSystem.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository repo;

	@Override
	public Team addTeam(Team team) {
		return repo.save(team);
	}

	@Override
	public Team updateTeamById(int id, Team team) throws TeamNotFoundException {
		Team updateTeam = findTeamById(id);
		updateTeam.setTeamName(team.getTeamName());
		updateTeam.setUserId(team.getUserId());
		return repo.save(updateTeam);
	}

	@Override
	public Team findTeamById(int id) throws TeamNotFoundException {
		Optional<Team> teams = repo.findById(id);
		if (teams.isPresent())
			return teams.get();
		else
			throw new TeamNotFoundException("TeamNotFound with id: " + id);
	}

	@Override
	public String deleteTeamById(int id) throws TeamNotFoundException {
		Optional<Team> teams = repo.findById(id);
		if (teams.isPresent()) {
			repo.delete(teams.get());
			return "deleted";
		} else {
			throw new TeamNotFoundException("TeamNotFound with id: " + id);
		}
	}

	@Override
	public List<Team> findAllTeams() {
		return repo.findAll();
	}

}
