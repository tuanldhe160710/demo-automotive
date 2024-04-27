package com.example.demoproject.web.rest;

import com.example.demoproject.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeviceResouces {

    private final VehicleService vehicleService;

    @PostMapping("/register")
    public String register(){
        return vehicleService.register();
    }

}
