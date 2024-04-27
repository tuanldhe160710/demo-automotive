package com.example.demoproject.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class VehicleRequest {

    private String name;

    private String type;

}
