package com.bezkoder.springjwt.account.services.impl;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.dto.response.UserResponseDto;
import com.bezkoder.springjwt.account.repository.RoleRepository;
import com.bezkoder.springjwt.account.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.repository.UserRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Lazy
    @Autowired
    PasswordEncoder passwordEncode;

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public String forgotPassword(String email) {

        Optional<User> userOptional = Optional
                .ofNullable(userRepository.findByEmail(email));

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        User user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        return user.getToken();
    }

    public String resetPassword(String email, String password) {

        User user = userRepository.findByEmail(email);
        if (user==null){
            throw new UsernameNotFoundException("email doesn't exist");
        }
        user.setPassword(passwordEncode.encode(password));
        user.setToken(null);
        user.setTokenCreationDate(null);

        userRepository.save(user);

        return "Your password successfully updated.";
    }

    /**
     * Generate unique token. You may add multiple parameters to create a strong
     * token.
     *
     * @return unique token
     */
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }

    public String addUser(UserDto userDto) {
        User user = UserConverter.toEntity(userDto);
        user.setPassword(passwordEncode.encode(userDto.getPassword()));
        if (userRepository.existsByEmail(userDto.getEmail())){
            User existingUser = userRepository.findByEmail(userDto.getEmail());
            user.setId(existingUser.getId());
        }
        userRepository.save(user);

        return "user is registered";
    }

    public String updateUser(UserDto userDto, Long id) {
        try {
            User existingUser = userRepository.findById(id).get();
            existingUser.setUsername(userDto.getUsername());
            existingUser.setPassword(passwordEncode.encode(userDto.getPassword()));
            existingUser.setEmail((userDto.getEmail()));

            userRepository.save(existingUser);

            return "User is updated";
        } catch (Exception e) {
            throw new RuntimeException("User with id = " + id + " doesn't exist ");
        }
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);

        return "User is deleted";
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserResponseDto dto = UserConverter.toUserDto(user);

        return dto;
    }

    public List<User> getPageOne() {

        // First page with 5 items
        Pageable paging = PageRequest.of(
                0, 2, Sort.by("id").ascending());
        Page<User> page = userRepository.findAll(paging);

        // Retrieve the items
        return page.getContent();
    }
}



