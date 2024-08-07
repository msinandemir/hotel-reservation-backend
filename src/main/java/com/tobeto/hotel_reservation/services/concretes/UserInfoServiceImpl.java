package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.UserInfo;
import com.tobeto.hotel_reservation.repositories.UserInfoRepository;
import com.tobeto.hotel_reservation.services.abstracts.UserInfoService;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.userInfo.*;
import com.tobeto.hotel_reservation.services.mappers.UserInfoMapper;
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
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserService userService;

    @Cacheable(cacheNames = "user_infos", key = "#root.methodName + #pageNumber + '_' + #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getAllUserInfosWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<UserInfo> userInfos = userInfoRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(userInfos);

        List<GetUserInfoResponse> responses = userInfos.stream().map(UserInfoMapper.INSTANCE::getResponseFromUserInfo).toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "user_info_id", key = "#root.methodName + #userInfoId", unless = "#result == null")
    @Override
    public GetUserInfoResponse getUserInfoById(Long userInfoId, String language) {
        UserInfo foundUserInfo = findUserInfoById(userInfoId, language);
        return UserInfoMapper.INSTANCE.getResponseFromUserInfo(foundUserInfo);
    }

    @Cacheable(cacheNames = "user_info_user_id", key = "#root.methodName + #userId", unless = "#result == null")
    @Override
    public GetUserInfoResponse getUserInfoByUserId(Long userId, String language) {
        userService.findUserById(userId, language);
        UserInfo foundUserInfoByUserId = userInfoRepository.findByUserId(userId);
        return UserInfoMapper.INSTANCE.getResponseFromUserInfo(foundUserInfoByUserId);
    }

    @CacheEvict(cacheNames = {"user_info_id", "user_infos", "user_info_user_id"}, allEntries = true)
    @Override
    public AddUserInfoResponse addUserInfo(AddUserInfoRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        UserInfo userInfo = UserInfoMapper.INSTANCE.userInfoFromAddRequest(request);
        UserInfo savedUserInfo = userInfoRepository.save(userInfo);
        return UserInfoMapper.INSTANCE.addResponseFromUserInfo(savedUserInfo);
    }

    @CachePut(cacheNames = "user_info_id", key = "'getUserInfoById' + #userInfoId", unless = "#result == null")
    @Override
    public UpdateUserInfoResponse updateUserInfoById(Long userInfoId, UpdateUserInfoRequest request, String language) {
        userService.findUserById(request.getUserId(), language);
        UserInfo foundUserInfo = findUserInfoById(userInfoId, language);
        UserInfo updatedUserInfo = UserInfoMapper.INSTANCE.userInfoFromUpdateRequest(request);
        updatedUserInfo.setId(foundUserInfo.getId());

        UserInfo savedUserInfo = userInfoRepository.save(updatedUserInfo);
        return UserInfoMapper.INSTANCE.updateResponseFromUserInfo(savedUserInfo);
    }

    @CacheEvict(cacheNames = {"user_info_id", "user_infos", "user_info_user_id"}, allEntries = true)
    @Override
    public void deleteUserInfoById(Long userInfoId, String language) {
        UserInfo foundUserInfo = findUserInfoById(userInfoId, language);
        userInfoRepository.deleteById(foundUserInfo.getId());
    }

    private UserInfo findUserInfoById(Long userInfoId, String language) {
        return userInfoRepository.findById(userInfoId).orElseThrow(() -> new BusinessException("error.userInfoNotFound", language));
    }
}
