package com.PS.userapi.dto;

import com.PS.userapi.model.*;
import lombok.*;

@Getter
@Data
@Builder // Lombok generates UserDTOBuilder automatically
@AllArgsConstructor
public class UserDTO {
    // Getters and setters for all fields
    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private Integer height;
    private Double weight;
    private String ssn;
    private String ein;
    private String university;
    private Hair hair;
    private Address address;
    private Bank bank;
    private Company company;
    private String domain;
    private String ip;
    private String macAddress;
    private String website;
    private Coordinates coordinates;

    // Empty constructor
    public UserDTO() {}

    public void setId(Long id) { this.id = id; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setMaidenName(String maidenName) { this.maidenName = maidenName; }

    public void setAge(Integer age) { this.age = age; }

    public void setGender(String gender) { this.gender = gender; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public void setImage(String image) { this.image = image; }

    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public void setHeight(Integer height) { this.height = height; }

    public void setWeight(Double weight) { this.weight = weight; }

    public void setSsn(String ssn) { this.ssn = ssn; }

    public void setEin(String ein) { this.ein = ein; }

    public void setUniversity(String university) { this.university = university; }

    public void setHair(Hair hair) { this.hair = hair; }

    public void setAddress(Address address) { this.address = address; }

    public void setBank(Bank bank) { this.bank = bank; }

    public void setCompany(Company company) { this.company = company; }

    public void setDomain(String domain) { this.domain = domain; }

    public void setIp(String ip) { this.ip = ip; }

    public void setMacAddress(String macAddress) { this.macAddress = macAddress; }

    public void setWebsite(String website) { this.website = website; }

    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    // Manual Builder Implementation
    public static class UserDTOBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String maidenName;
        private Integer age;
        private String gender;
        private String email;
        private String phone;
        private String username;
        private String password;
        private String birthDate;
        private String image;
        private String bloodGroup;
        private Integer height;
        private Double weight;
        private String ssn;
        private String ein;
        private String university;
        private Hair hair;
        private Address address;
        private Bank bank;
        private Company company;
        private String domain;
        private String ip;
        private String macAddress;
        private String website;
        private Coordinates coordinates;

        public UserDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder maidenName(String maidenName) {
            this.maidenName = maidenName;
            return this;
        }

        public UserDTOBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public UserDTOBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserDTOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDTOBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserDTOBuilder image(String image) {
            this.image = image;
            return this;
        }

        public UserDTOBuilder bloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
            return this;
        }

        public UserDTOBuilder height(Integer height) {
            this.height = height;
            return this;
        }

        public UserDTOBuilder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public UserDTOBuilder ssn(String ssn) {
            this.ssn = ssn;
            return this;
        }

        public UserDTOBuilder ein(String ein) {
            this.ein = ein;
            return this;
        }

        public UserDTOBuilder university(String university) {
            this.university = university;
            return this;
        }

        public UserDTOBuilder hair(Hair hair) {
            this.hair = hair;
            return this;
        }

        public UserDTOBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public UserDTOBuilder bank(Bank bank) {
            this.bank = bank;
            return this;
        }

        public UserDTOBuilder company(Company company) {
            this.company = company;
            return this;
        }

        public UserDTOBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public UserDTOBuilder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public UserDTOBuilder macAddress(String macAddress) {
            this.macAddress = macAddress;
            return this;
        }

        public UserDTOBuilder website(String website) {
            this.website = website;
            return this;
        }

        public UserDTOBuilder coordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public UserDTO build() {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(id);
            userDTO.setFirstName(firstName);
            userDTO.setLastName(lastName);
            userDTO.setMaidenName(maidenName);
            userDTO.setAge(age);
            userDTO.setGender(gender);
            userDTO.setEmail(email);
            userDTO.setPhone(phone);
            userDTO.setUsername(username);
            userDTO.setPassword(password);
            userDTO.setBirthDate(birthDate);
            userDTO.setImage(image);
            userDTO.setBloodGroup(bloodGroup);
            userDTO.setHeight(height);
            userDTO.setWeight(weight);
            userDTO.setSsn(ssn);
            userDTO.setEin(ein);
            userDTO.setUniversity(university);
            userDTO.setHair(hair);
            userDTO.setAddress(address);
            userDTO.setBank(bank);
            userDTO.setCompany(company);
            userDTO.setDomain(domain);
            userDTO.setIp(ip);
            userDTO.setMacAddress(macAddress);
            userDTO.setWebsite(website);
            userDTO.setCoordinates(coordinates);
            return userDTO;
        }
    }

    // Static method to create a builder
    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }
}