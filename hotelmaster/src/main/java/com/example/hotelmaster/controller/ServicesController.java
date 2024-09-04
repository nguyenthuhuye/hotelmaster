package com.example.hotelmaster.controller;

import com.example.hotelmaster.dto.request.ServicesRequest;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.service.ServicesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ServicesController {
    ServicesService servicesService;

    @PostMapping
    Services createServices(@RequestBody ServicesRequest request) {
        return servicesService.createServices(request);
    }

    @GetMapping
    List<Services> getAllServices() {
        return servicesService.getAllServices();
    }

    @GetMapping("/{Id}")
    Services getServices(@PathVariable("Id") String Id) {
        return servicesService.getServices(Id);
    }

    @PutMapping("/{Id}")
    Services updateServices(@PathVariable String Id, @RequestBody ServicesRequest request) {
        return servicesService.updateServices(Id, request);
    }

    @DeleteMapping("/{Id}")
    String deleteServices(@PathVariable String Id) {
        servicesService.deleteServices(Id);
        return "Services deleted";
    }
}
