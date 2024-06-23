package com.tobeto.hotel_reservation.services.abstracts;

import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import org.springframework.data.domain.Sort;

public interface UserInfoService {
    EntityWithPagination getAllUserInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection);

    GetUserInfoResponse getUserInfoById(Long userInfoId, String language);

    AddUserInfoResponse addUserInfo(AddUserInfoRequest request, String language);

    UpdateUserInfoResponse updateUserInfoById(Long userInfoId, UpdateUserInfoRequest request, String language);

    void deleteUserInfoById(Long userInfoId, String language);
}
