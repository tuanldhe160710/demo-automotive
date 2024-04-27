package com.example.demoproject.service.impl;

import com.example.demoproject.entity.Vehicle;
import com.example.demoproject.repository.VehicleRepository;
import com.example.demoproject.service.VehicleService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultVehicleService implements VehicleService {
    @Value("${keycloak.realm}")
    private String realmName;

    private final Keycloak keycloak;

    private final VehicleRepository vehicleRepository;
    @Override
    public String register() {
        try{
            //init vehicle infomation
            Vehicle vehicle = Vehicle.builder()
                    .vin(UUID.randomUUID().toString())
                    .build();

            RealmResource realmResource = keycloak.realm(realmName);
            UsersResource usersResource = realmResource.users();


            //create user infomation
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(false);
            userRepresentation.setUsername(vehicle.getVin());
            userRepresentation.singleAttribute("year", String.valueOf(LocalDate.now().getYear()));
            userRepresentation.singleAttribute("model", "tesla");
            userRepresentation.singleAttribute("vin", String.valueOf(vehicle.getVin()));


            //register in keycloak
            Response createdVehicle = usersResource.create(userRepresentation);


            //validate
            if(Objects.equals(201, createdVehicle.getStatus()) && usersResource.search(vehicle.getVin()).get(0).getId() != null){
                //save in database
                vehicle.setVid(usersResource.search(vehicle.getVin()).get(0).getId());
                vehicleRepository.save(vehicle);
                return "Register successfully!";
            }


        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        return "Register failed!";
    }
}
