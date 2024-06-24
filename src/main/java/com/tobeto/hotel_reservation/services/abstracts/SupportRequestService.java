package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import org.springframework.data.domain.Sort;

public interface SupportRequestService {
    EntityWithPagination getAllSupportRequestsWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetSupportRequestResponse getSupportRequestById(Long supportRequestId, String language);

    AddSupportRequestResponse addSupportRequest(AddSupportRequestRequest request, String language);

    UpdateSupportRequestResponse updateSupportRequestById(Long supportRequestId, UpdateSupportRequestRequest request, String language);

    void deleteSupportRequestById(Long supportRequestId, String language);

}
