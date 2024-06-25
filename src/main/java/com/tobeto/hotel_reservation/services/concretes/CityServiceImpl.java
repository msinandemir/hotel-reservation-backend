package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.entities.concretes.City;
import com.tobeto.hotel_reservation.repositories.CityRepository;
import com.tobeto.hotel_reservation.services.abstracts.CityService;
import com.tobeto.hotel_reservation.services.abstracts.CountryService;
import com.tobeto.hotel_reservation.services.dtos.city.*;
import com.tobeto.hotel_reservation.services.mappers.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryService countryService;

    @Cacheable(cacheNames = "cities", key = "#root.methodName", unless = "#result == null")
    @Override
    public List<GetCityResponse> getAllCities() {
        return cityRepository.findAll().stream()
                .map(CityMapper.INSTANCE::getResponseFromCity)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "city_id", key = "#root.methodName + #cityId", unless = "#result == null")
    @Override
    public GetCityResponse getCityById(Long cityId, String language) {
        City foundCity = findCityById(cityId, language);
        return CityMapper.INSTANCE.getResponseFromCity(foundCity);
    }

    @CacheEvict(cacheNames = {"cities", "city_id"}, allEntries = true)
    @Override
    public AddCityResponse addCity(AddCityRequest request, String language) {
        countryService.findCountryById(request.getCountryId(), language);
        City city = CityMapper.INSTANCE.cityFromAddRequest(request);
        City savedCity = cityRepository.save(city);
        return CityMapper.INSTANCE.addResponseFromCity(savedCity);
    }

    @CachePut(cacheNames = "city_id", key = "getCityById + #request.id", unless = "#result == null")
    @Override
    public UpdateCityResponse updateCityById(Long cityId, UpdateCityRequest request, String language) {
        countryService.findCountryById(request.getCountryId(), language);
        City foundCity = findCityById(cityId, language);
        City updatedCity = CityMapper.INSTANCE.cityFromUpdateRequest(request);
        updatedCity.setId(foundCity.getId());

        City savedCity = cityRepository.save(updatedCity);
        return CityMapper.INSTANCE.updateResponseFromCity(savedCity);
    }

    @CacheEvict(cacheNames = {"cities", "city_id"}, allEntries = true)
    @Override
    public void deleteCityById(Long cityId, String language) {
        City foundCity = findCityById(cityId, language);
        cityRepository.deleteById(foundCity.getId());
    }

    @Override
    public City findCityById(Long cityId, String language) {
        return cityRepository.findById(cityId).orElseThrow(() -> new BusinessException("error.cityNotFound", language));
    }
}
