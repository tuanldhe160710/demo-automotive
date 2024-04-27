package com.example.demoproject.repository;

import com.example.demoproject.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
