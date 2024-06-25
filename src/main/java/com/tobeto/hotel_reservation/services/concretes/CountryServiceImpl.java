package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.entities.concretes.Country;
import com.tobeto.hotel_reservation.repositories.CountryRepository;
import com.tobeto.hotel_reservation.services.abstracts.CountryService;
import com.tobeto.hotel_reservation.services.dtos.country.*;
import com.tobeto.hotel_reservation.services.mappers.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Cacheable(cacheNames = "countries", key = "#root.methodName", unless = "#result == null")
    @Override
    public List<GetCountryResponse> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(CountryMapper.INSTANCE::getResponseFromCountry)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "country_id", key = "#root.methodName + #countryId", unless = "#result == null")
    @Override
    public GetCountryResponse getCountryById(Long countryId, String language) {
        Country foundCountry = findCountryById(countryId, language);
        return CountryMapper.INSTANCE.getResponseFromCountry(foundCountry);
    }

    @CacheEvict(cacheNames = {"countries", "country_id"}, allEntries = true)
    @Override
    public AddCountryResponse addCountry(AddCountryRequest request) {
        Country country = CountryMapper.INSTANCE.countryFromAddRequest(request);
        Country savedCountry = countryRepository.save(country);
        return CountryMapper.INSTANCE.addResponseFromCountry(savedCountry);
    }

    @CachePut(cacheNames = "country_id", key = "getCountryById + #request.id", unless = "#result == null")
    @Override
    public UpdateCountryResponse updateCountryById(Long countryId, UpdateCountryRequest request, String language) {
        Country foundCountry = findCountryById(countryId, language);
        Country updatedCountry = CountryMapper.INSTANCE.countryFromUpdateRequest(request);
        updatedCountry.setId(foundCountry.getId());

        Country savedCountry = countryRepository.save(updatedCountry);
        return CountryMapper.INSTANCE.updateResponseFromCountry(savedCountry);
    }

    @CacheEvict(cacheNames = {"countries", "country_id"}, allEntries = true)
    @Override
    public void deleteCountryById(Long countryId, String language) {
        Country foundCountry = findCountryById(countryId, language);
        countryRepository.deleteById(foundCountry.getId());
    }

    @Override
    public Country findCountryById(Long countryId, String language) {
        return countryRepository.findById(countryId).orElseThrow(() -> new BusinessException("error.countryNotFound", language));
    }
}
