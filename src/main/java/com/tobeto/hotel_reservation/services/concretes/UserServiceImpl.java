package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.repositories.UserRepository;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import com.tobeto.hotel_reservation.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Cacheable(cacheNames = "users", key = "#root.methodName + #pageNumber + '_' + #pageSize", unless = "#result == null")
    @Override
    public EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection) {
        Sort sorting = Sort.by(sortDirection, "createdAt");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sorting);
        Page<User> users = userRepository.findAll(pageable);

        EntityWithPagination pagination = new EntityWithPagination();
        pagination.mappedFromPageWithoutContent(users);

        List<GetUserResponse> responses = users.stream()
                .map(UserMapper.INSTANCE::getResponseFromUser)
                .toList();
        pagination.setContent(responses);
        return pagination;
    }

    @Cacheable(cacheNames = "user_id", key = "#root.methodName + #userId", unless = "#result == null")
    @Override
    public GetUserResponse getUserById(Long userId, String language) {
        User foundUser = findUserById(userId, language);
        return UserMapper.INSTANCE.getResponseFromUser(foundUser);
    }

    @CacheEvict(cacheNames = {"user_id", "users"}, allEntries = true)
    @Override
    public AddUserResponse addUser(AddUserRequest request) {
        User user = UserMapper.INSTANCE.userFromAddRequest(request);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.addResponseFromUser(savedUser);
    }

    @CachePut(cacheNames = "user_id", key = "'getUserById' + #request.id", unless = "#result == null")
    @Override
    public UpdateUserResponse updateUserById(Long userId, UpdateUserRequest request, String language) {
        User foundUser = findUserById(userId, language);
        User updatedUser = UserMapper.INSTANCE.userFromUpdateRequest(request);
        updatedUser.setId(foundUser.getId());

        User savedUser = userRepository.save(updatedUser);
        return UserMapper.INSTANCE.updateResponseFromUser(savedUser);
    }

    @CacheEvict(cacheNames = {"user_id", "users"}, allEntries = true)
    @Override
    public void deleteUserById(Long userId, String language) {
        User foundUser = findUserById(userId, language);
        userRepository.deleteById(foundUser.getId());
    }

    @Override
    public User findUserById(Long userId, String language) {
        return userRepository.findById(userId).orElseThrow(() -> new BusinessException("error.userNotFound", language));
    }
}
