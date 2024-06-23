package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.supportRequest.*;
import org.springframework.data.domain.Sort;

public interface SupportRequestService {
    EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetSupportRequestResponse getSupportRequestById(Long supportRequestId, String language);

    AddSupportRequestResponse addSupportRequest(AddSupportRequestRequest request);

    UpdateSupportRequestResponse updateSupportRequestById(Long supportRequestId, UpdateSupportRequestRequest request, String language);

    void deleteSupportRequestById(Long supportRequestId, String language);

}
