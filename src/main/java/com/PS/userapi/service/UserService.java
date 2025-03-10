package com.PS.userapi.service;

import com.PS.userapi.dto.UserDTO;
import com.PS.userapi.exception.ResourceNotFoundException;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {

    /**
     * Load all users from an external API into the database
     * @return Number of users loaded
     */
    int loadUsersFromExternalApi();

    /**
     * Get all users
     * @return List of users
     */
    List<UserDTO> getAllUsers();

    /**
     * Get user by ID
     * @param id User ID
     * @return User details
     * @throws ResourceNotFoundException if user is not found
     */
    UserDTO getUserById(Long id);

    /**
     * Get user by email
     * @param email User email
     * @return User details
     * @throws ResourceNotFoundException if user is not found
     */
    UserDTO getUserByEmail(String email);

    /**
     * Search users by free text (matches firstName or lastName)
     * @param searchTerm Search term to match against firstName or lastName
     * @return List of matching users
     */
    List<UserDTO> searchUsers(@NonNull String searchTerm);
}