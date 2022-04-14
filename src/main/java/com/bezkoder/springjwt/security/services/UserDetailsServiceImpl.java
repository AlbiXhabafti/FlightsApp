package com.bezkoder.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;

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

private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

  /*public void updateResetPasswordToken(String token, String email) throws UsernameNotFoundException {
    User customer = userRepository.findByEmail(email);
    if (customer != null) {
      customer.setToken(token);
      userRepository.save(customer);
    } else {
      throw new UsernameNotFoundException("Could not find any customer with the email " + email);
    }
  }

  public User getByResetPasswordToken(String token) {
    return userRepository.findByToken(token);
  }

  public void updatePassword(User user, String newPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(newPassword);
    user.setPassword(encodedPassword);

    user.setToken(null);
    userRepository.save(user);
  }
  */

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

  public String resetPassword(String token, String password) {

    Optional<User> userOptional = Optional
            .ofNullable(userRepository.findByToken(token));

    if (!userOptional.isPresent()) {
      return "Invalid token.";
    }

    LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

    if (isTokenExpired(tokenCreationDate)) {
      return "Token expired.";

    }

    User user = userOptional.get();

    //String hashedPassword = passwordEncoder.encode(password);

    user.setPassword( password);
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

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(User user, Long id){
    User existingUser = userRepository.findById(id).get();

    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    existingUser.setEmail((user.getEmail()));


    userRepository.save(existingUser);

    return existingUser;
  }
  public void deleteUser(Long id){

    userRepository.findById(id);

    userRepository.deleteById(id);
  }
public User getUserById(Long id){
    return userRepository.findById(id).get();
}
  public List<User> getPageOne()
  {

    // First page with 5 items
    Pageable paging = PageRequest.of(
            0, 2, Sort.by("id").ascending());
    Page<User> page = userRepository.findAll(paging);

    // Retrieve the items
    return page.getContent();
  }
}



