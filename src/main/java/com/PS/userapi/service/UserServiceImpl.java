package com.PS.userapi.service;

import com.PS.userapi.dto.UserDTO;
import com.PS.userapi.exception.ResourceNotFoundException;
import com.PS.userapi.model.*;
import com.PS.userapi.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${external.api.users.url}")
    private String externalApiUrl;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        log.info("External API URL: {}", externalApiUrl);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int loadUsersFromExternalApi() {
        entityManager.clear(); // Clears persistence context to prevent stale data issues
        log.info("Loading users from external API: {}", externalApiUrl);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(externalApiUrl, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Failed to load users from external API. Status code: {}", response.getStatusCode());
                return 0;
            }

            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode usersNode = root.has("users") ? root.get("users") : root;

            if (usersNode == null || !usersNode.isArray()) {
                log.error("Invalid response format from external API");
                return 0;
            }

            List<User> users = new ArrayList<>();
            for (JsonNode userNode : usersNode) {
                try {
                    Long userId = userNode.has("id") ? userNode.get("id").asLong() : null;
                    Optional<User> existingUserOpt = userId != null ? userRepository.findById(userId) : Optional.empty();
                    User user = existingUserOpt.orElse(new User());

                    if (userId != null) user.setId(userId);
                    if (userNode.has("firstName")) user.setFirstName(userNode.get("firstName").asText());
                    if (userNode.has("lastName")) user.setLastName(userNode.get("lastName").asText());
                    if (userNode.has("email")) user.setEmail(userNode.get("email").asText());
                    if (userNode.has("phone")) user.setPhone(userNode.get("phone").asText());
                    if (userNode.has("username")) user.setUsername(userNode.get("username").asText());
                    if (userNode.has("password")) user.setPassword(userNode.get("password").asText());
                    if (userNode.has("gender")) user.setGender(userNode.get("gender").asText());
                    if (userNode.has("image")) user.setImage(userNode.get("image").asText());
                    if (userNode.has("age")) user.setAge(userNode.get("age").asInt());

                    if (userNode.has("maidenName")) user.setMaidenName(userNode.get("maidenName").asText());
                    if (userNode.has("birthDate")) user.setBirthDate(userNode.get("birthDate").asText());
                    if (userNode.has("bloodGroup")) user.setBloodGroup(userNode.get("bloodGroup").asText());
                    if (userNode.has("height")) user.setHeight(userNode.get("height").asInt());
                    if (userNode.has("weight")) user.setWeight(userNode.get("weight").asDouble());
                    if (userNode.has("ssn")) user.setSsn(userNode.get("ssn").asText());
                    if (userNode.has("ein")) user.setEin(userNode.get("ein").asText());
                    if (userNode.has("university")) user.setUniversity(userNode.get("university").asText());
                    if (userNode.has("domain")) user.setDomain(userNode.get("domain").asText());
                    if (userNode.has("ip")) user.setIp(userNode.get("ip").asText());
                    if (userNode.has("macAddress")) user.setMacAddress(userNode.get("macAddress").asText());
                    if (userNode.has("website")) user.setWebsite(userNode.get("website").asText());

                    // **1. Mapping Address**
                    if (userNode.has("address")) {
                        JsonNode addressNode = userNode.get("address");
                        Address address = new Address();
                        if (addressNode.has("address")) address.setAddress(addressNode.get("address").asText());
                        if (addressNode.has("city")) address.setCity(addressNode.get("city").asText());
                        if (addressNode.has("postalCode")) address.setPostalCode(addressNode.get("postalCode").asText());
                        if (addressNode.has("state")) address.setState(addressNode.get("state").asText());
                        user.setAddress(address);
                    }

                    // **2. Mapping Bank**
                    if (userNode.has("bank")) {
                        JsonNode bankNode = userNode.get("bank");
                        Bank bank = new Bank();
                        if (bankNode.has("cardExpire")) bank.setCardExpire(bankNode.get("cardExpire").asText());
                        if (bankNode.has("cardNumber")) bank.setCardNumber(bankNode.get("cardNumber").asText());
                        if (bankNode.has("cardType")) bank.setCardType(bankNode.get("cardType").asText());
                        if (bankNode.has("currency")) bank.setCurrency(bankNode.get("currency").asText());
                        if (bankNode.has("iban")) bank.setIban(bankNode.get("iban").asText());
                        user.setBank(bank);
                    }

                    // **3. Mapping Company**
                    if (userNode.has("company")) {
                        JsonNode companyNode = userNode.get("company");
                        Company company = new Company();
                        if (companyNode.has("name")) company.setName(companyNode.get("name").asText());
                        if (companyNode.has("department")) company.setDepartment(companyNode.get("department").asText());
                        if (companyNode.has("title")) company.setTitle(companyNode.get("title").asText());

                        // **Company has an Address**
                        if (companyNode.has("address")) {
                            JsonNode companyAddressNode = companyNode.get("address");
                            Address companyAddress = new Address();
                            if (companyAddressNode.has("address")) companyAddress.setAddress(companyAddressNode.get("address").asText());
                            if (companyAddressNode.has("city")) companyAddress.setCity(companyAddressNode.get("city").asText());
                            if (companyAddressNode.has("postalCode")) companyAddress.setPostalCode(companyAddressNode.get("postalCode").asText());
                            if (companyAddressNode.has("state")) companyAddress.setState(companyAddressNode.get("state").asText());
                            company.setAddress(companyAddress);
                        }

                        user.setCompany(company);
                    }

                    // **4. Mapping Coordinates**
                    if (userNode.has("coordinates")) {
                        JsonNode coordinatesNode = userNode.get("coordinates");
                        Coordinates coordinates = new Coordinates();
                        if (coordinatesNode.has("lat")) coordinates.setLat(coordinatesNode.get("lat").asDouble());
                        if (coordinatesNode.has("lng")) coordinates.setLng(coordinatesNode.get("lng").asDouble());
                        user.setCoordinates(coordinates);
                    }

                    // **5. Mapping Hair**
                    if (userNode.has("hair")) {
                        JsonNode hairNode = userNode.get("hair");
                        Hair hair = new Hair();
                        if (hairNode.has("color")) hair.setColor(hairNode.get("color").asText());
                        if (hairNode.has("type")) hair.setType(hairNode.get("type").asText());
                        user.setHair(hair);
                    }




                    users.add(user);
                    log.debug("Processed user: {}", user);
                } catch (Exception e) {
                    log.error("Error processing user JSON node: {}", userNode, e);
                }
            }

            if (users.isEmpty()) {
                log.warn("No users found in the API response");
                return 0;
            }

            try {
                userRepository.saveAllAndFlush(users);
            } catch (OptimisticLockException e) {
                log.error("Optimistic Locking Failure: Retrying save operation", e);
                // Re-fetch each user and apply updates again
                List<User> retryUsers = users.stream()
                        .map(user -> {
                            if (user.getId() != null) {
                                return userRepository.findById(user.getId())
                                        .map(existingUser -> {
                                            // Apply changes from the current user to the re-fetched entity
                                            BeanUtils.copyProperties(user, existingUser, "version");
                                            return existingUser;
                                        })
                                        .orElse(user); // If not found, proceed with original (may insert new)
                            }
                            return user;
                        })
                        .collect(Collectors.toList());
                userRepository.saveAllAndFlush(retryUsers);
            }

            log.info("Successfully loaded and saved {} users from external API", users.size());
            return users.size();

        } catch (RestClientException ex) {
            log.error("Error connecting to external API", ex);
            return 0;
        } catch (Exception ex) {
            log.error("Error processing users from external API", ex);
            return 0;
        }
    }





    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> searchUsers(String searchTerm) {
        return searchTerm == null || searchTerm.trim().isEmpty()
                ? getAllUsers()
                : userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchTerm, searchTerm)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDto(User user) {
        if (user == null) return null;
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
