package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.abstracts.SupportRequestService;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import org.springframework.data.domain.Sort;

public class SupportRequestServiceImpl implements SupportRequestService {
    @Override
    public EntityWithPagination getAllSupportRequestsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    public GetSupportRequestResponse getSupportRequestById(Long supportRequestId, String language) {
        return null;
    }

    @Override
    public AddSupportRequestResponse addSupportRequest(AddSupportRequestRequest request) {
        return null;
    }

    @Override
    public UpdateSupportRequestResponse updateSupportRequestById(Long supportRequestId, UpdateSupportRequestRequest request, String language) {
        return null;
    }

    @Override
    public void deleteSupportRequestById(Long supportRequestId, String language) {

    }
}
