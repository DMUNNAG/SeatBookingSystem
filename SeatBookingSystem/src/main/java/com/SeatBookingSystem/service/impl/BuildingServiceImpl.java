package com.SeatBookingSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeatBookingSystem.entity.Building;
import com.SeatBookingSystem.exception.BuildingNotFoundException;
import com.SeatBookingSystem.repository.BuildingRepository;
import com.SeatBookingSystem.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public Building addBuilding(Building building) {
		return buildingRepository.save(building);
	}

	@Override
	public Building updateBuildingById(int id, Building building) throws BuildingNotFoundException {
		Optional<Building> optionalBuilding = buildingRepository.findById(id);
		if (optionalBuilding.isPresent()) {
			building.setId(id);
			return buildingRepository.save(building);
		}
		throw new BuildingNotFoundException("Building not found with ID: " + id);
	}

	@Override
	public Building findBuildingById(int id) throws BuildingNotFoundException {
		return buildingRepository.findById(id)
				.orElseThrow(() -> new BuildingNotFoundException("Building not found with ID: " + id));
	}

	@Override
	public String deleteBuildingById(int id) throws BuildingNotFoundException {
		Optional<Building> optionalBuilding = buildingRepository.findById(id);
		if (optionalBuilding.isPresent()) {
			buildingRepository.deleteById(id);
			return "Building deleted successfully.";
		}
		throw new BuildingNotFoundException("Building not found with ID: " + id);
	}

	@Override
	public List<Building> findAllBuildings() {
		return buildingRepository.findAll();
	}
}
