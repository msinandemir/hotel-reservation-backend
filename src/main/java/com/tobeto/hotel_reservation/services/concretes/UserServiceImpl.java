package com.tobeto.hotel_reservation.services.concretes;

import com.tobeto.hotel_reservation.core.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation.core.models.EntityWithPagination;
import com.tobeto.hotel_reservation.core.models.WelcomeEmail;
import com.tobeto.hotel_reservation.entities.concretes.User;
import com.tobeto.hotel_reservation.entities.enums.Role;
import com.tobeto.hotel_reservation.repositories.UserRepository;
import com.tobeto.hotel_reservation.services.abstracts.AddressService;
import com.tobeto.hotel_reservation.services.abstracts.EmailGateway;
import com.tobeto.hotel_reservation.services.abstracts.UserService;
import com.tobeto.hotel_reservation.services.dtos.user.*;
import com.tobeto.hotel_reservation.services.mappers.EmailMapper;
import com.tobeto.hotel_reservation.services.mappers.UserMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailGateway emailGateway;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    @Cacheable(cacheNames = "users", key = "#root.methodName + #pageNumber + '_' + #pageSize + '_' + #sortDirection + '_' + #sortBy", unless = "#result == null")
    @Override
    public EntityWithPagination getAllUsersWithPagination(int pageNumber, int pageSize, Sort.Direction sortDirection, String sortBy) {
        Sort sorting = Sort.by(sortDirection, sortBy);
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
    public AddUserResponse addUser(AddUserRequest request, String language) throws MessagingException {
        checkUserExistsByEmail(request.getEmail(), language);
        addressService.findAddressById(request.getAddressId(), language);
        User user = UserMapper.INSTANCE.userFromAddRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(Role.ROLE_USER);
        user.setAuthorities(roles);

        User savedUser = userRepository.save(user);

        sendWelcomeEmail(language, savedUser);
        return UserMapper.INSTANCE.addResponseFromUser(savedUser);
    }

    @CachePut(cacheNames = "user_id", key = "'getUserById' + #userId", unless = "#result == null")
    @Override
    public UpdateUserResponse updateUserById(Long userId, UpdateUserRequest request, String language) {
        checkUserExistsByEmail(request.getEmail(), language);
        addressService.findAddressById(request.getAddressId(), language);
        User foundUser = findUserById(userId, language);
        User updatedUser = UserMapper.INSTANCE.userFromUpdateRequest(request);
        updatedUser.setId(foundUser.getId());

        User savedUser = userRepository.save(updatedUser);
        return UserMapper.INSTANCE.updateResponseFromUser(savedUser);
    }

    @CacheEvict(cacheNames = {"user_id", "users"}, allEntries = true)
    @Override
    public ChangeUserRoleResponse changeUserRoleById(Long userId, Role role, String language) {
        User user = findUserById(userId, language);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setAuthorities(roles);

        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.changeRolesResponseFromUser(savedUser);
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

    @Override
    public User loadUserByEmail(String email, String language) {
        return userRepository.findByEmail(email).orElseThrow(() -> new BusinessException("error.emailExists", language));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String language = getLanguageFromRequestContextHolder();
        return userRepository.findByEmail(username).orElseThrow(() -> new BusinessException("error.emailExists", language));
    }

    private void checkUserExistsByEmail(String email, String language) {
        boolean result = userRepository.existsByEmail(email);
        if (result)
            throw new BusinessException("error.emailExists", language);
    }

    private void sendWelcomeEmail(String language, User savedUser) throws MessagingException {
        WelcomeEmail welcomeEmail = EmailMapper.INSTANCE.welcomeEmailFromUser(savedUser);
        emailGateway.sendWelcomeEmail(welcomeEmail, language);
    }

    private String getLanguageFromRequestContextHolder() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return request.getHeader("lang");
    }
}
