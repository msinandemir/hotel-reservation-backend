package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;

public interface SupportRequestService {
    EntityWithPagination getAllSupportRequestsWithPagination(PaginationRequest paginationRequest);

    GetSupportRequestResponse getSupportRequestById(Long supportRequestId, String language);

    EntityWithPagination getSupportRequestByUserIdWithPagination(Long userId, PaginationRequest paginationRequest);

    AddSupportRequestResponse addSupportRequest(AddSupportRequestRequest request, String language);

    UpdateSupportRequestResponse updateSupportRequestById(Long supportRequestId, UpdateSupportRequestRequest request, String language);

    void deleteSupportRequestById(Long supportRequestId, String language);

}
