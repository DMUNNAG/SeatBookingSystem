package com.SeatBookingSystem.service;

import java.util.List;

import com.SeatBookingSystem.entity.Team;
import com.SeatBookingSystem.exception.TeamNotFoundException;

public interface TeamService {

	Team addTeam(Team team);

	Team updateTeamById(int id, Team team) throws TeamNotFoundException;

	Team findTeamById(int id) throws TeamNotFoundException;

	String deleteTeamById(int id) throws TeamNotFoundException;

	List<Team> findAllTeams();
}
