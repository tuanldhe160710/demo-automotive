package com.example.demoproject.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class VehicleResponse {

    private String id;

    private String name;

    private String type;

}
