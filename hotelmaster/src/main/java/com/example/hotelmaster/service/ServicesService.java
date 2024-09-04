package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.RoomTypesRequest;
import com.example.hotelmaster.dto.request.ServicesRequest;
import com.example.hotelmaster.entity.RoomTypes;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.mapper.ServicesMapper;
import com.example.hotelmaster.repository.ServicesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ServicesService {
    ServicesRepository servicesRepository;
    ServicesMapper servicesMapper;

    public Services createServices(ServicesRequest request) {

        Services services = servicesMapper.toService(request);

        return servicesRepository.save(services);
    }


    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public Services getServices(String id) {
        return servicesRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Services not found"));
    }

    public Services updateServices(String id, ServicesRequest request) {
        Services services = getServices(id);
        services = servicesMapper.toService(request);
        return servicesRepository.save(services);
    }


    public void deleteServices(String id) {
        servicesRepository.deleteById(id);
    }
}
