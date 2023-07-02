package com.SeatBookingSystem.service;

import java.util.List;

import com.SeatBookingSystem.entity.Building;
import com.SeatBookingSystem.exception.BuildingNotFoundException;

public interface BuildingService {
	Building addBuilding(Building Building);

	Building updateBuildingById(int id, Building Building) throws BuildingNotFoundException;

	Building findBuildingById(int id) throws BuildingNotFoundException;

	String deleteBuildingById(int id) throws BuildingNotFoundException;

	List<Building> findAllBuildings();
}
