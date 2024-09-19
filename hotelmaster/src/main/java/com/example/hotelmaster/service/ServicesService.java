package com.example.hotelmaster.service;

import com.example.hotelmaster.dto.request.RoomTypesRequest;
import com.example.hotelmaster.dto.request.ServicesRequest;
import com.example.hotelmaster.entity.RoomTypes;
import com.example.hotelmaster.entity.Services;
import com.example.hotelmaster.entity.User;
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

    public Services getServices(Long id) {
        return servicesRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Services not found"));
    }

    public Services updateServices(Long id, ServicesRequest request) {
        Services services = getServices(id);
        services.setServiceName(request.getServiceName());
        services.setDescription(request.getDescription());
        services.setPrice(request.getPrice());
        return servicesRepository.save(services);
    }


    public void deleteServices(Long id) {

        servicesRepository.deleteById(id);
    }
}