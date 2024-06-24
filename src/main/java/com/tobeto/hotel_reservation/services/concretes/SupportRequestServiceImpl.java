package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.SupportRequest;
import com.tobeto.hotel_reservation.repositories.SupportRequestRepository;
import com.tobeto.hotel_reservation.services.abstracts.SupportRequestService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import com.tobeto.hotel_reservation.services.mappers.SupportRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportRequestServiceImpl implements SupportRequestService {
    private final SupportRequestRepository supportRequestRepository;
    private final UserService userService;

    @Cacheable(cacheNames = "support_requests", key = "#root.methodName + #pageNumber + '_' #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllSupportRequestsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<SupportRequest> supportRequests = supportRequestRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(supportRequests);

        List<GetSupportRequestResponse> responses = supportRequests.stream()
                .map(SupportRequestMapper.INSTANCE::getResponseFromSupportRequest)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "support_request_id", key = "#root.methodName + #supportRequestId", unless = "#result == null")
    @Override
    public GetSupportRequestResponse getSupportRequestById(Long supportRequestId, String language) {
        SupportRequest foundSupportRequest = findSupportRequestById(supportRequestId, language);
        return SupportRequestMapper.INSTANCE.getResponseFromSupportRequest(foundSupportRequest);
    }

    @CacheEvict(cacheNames = {"support_request_id", "support_requests"}, allEntries = true)
    @Override
    public AddSupportRequestResponse addSupportRequest(AddSupportRequestRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        SupportRequest supportRequest = SupportRequestMapper.INSTANCE.supportRequestFromAddRequest(request);
        SupportRequest savedSupportRequest = supportRequestRepository.save(supportRequest);
        return SupportRequestMapper.INSTANCE.addResponseFromSupportRequest(savedSupportRequest);
    }

    @CachePut(cacheNames = "support_request_id", key = "getSupportRequestById + #request.id", unless = "#result == null")
    @Override
    public UpdateSupportRequestResponse updateSupportRequestById(Long supportRequestId, UpdateSupportRequestRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        SupportRequest foundSupportRequest = findSupportRequestById(supportRequestId, language);
        SupportRequest updatedSupportRequest = SupportRequestMapper.INSTANCE.supportRequestFromUpdateRequest(request);
        updatedSupportRequest.setId(foundSupportRequest.getId());

        SupportRequest savedSupportRequest = supportRequestRepository.save(updatedSupportRequest);
        return SupportRequestMapper.INSTANCE.updateResponseFromSupportRequest(savedSupportRequest);
    }

    @CacheEvict(cacheNames = {"support_request_id", "support_requests"}, allEntries = true)
    @Override
    public void deleteSupportRequestById(Long supportRequestId, String language) {
        SupportRequest foundSupportRequest = findSupportRequestById(supportRequestId, language);
        supportRequestRepository.deleteById(foundSupportRequest.getId());
    }

    private SupportRequest findSupportRequestById(Long supportRequestId, String language) {
        return supportRequestRepository.findById(supportRequestId).orElseThrow(() -> new BusinessException("error.supportRequestNotFound", language));
    }
}
