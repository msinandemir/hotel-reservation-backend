package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.PaginationRequest;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;

public interface UserInfoService {
    EntityWithPagination getAllUserInfosWithPagination(PaginationRequest paginationRequest);

    GetUserInfoResponse getUserInfoById(Long userInfoId, String language);

    AddUserInfoResponse addUserInfo(AddUserInfoRequest request, String language);

    UpdateUserInfoResponse updateUserInfoById(Long userInfoId, UpdateUserInfoRequest request, String language);

    void deleteUserInfoById(Long userInfoId, String language);
}
