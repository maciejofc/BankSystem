package pl.maciejowsky.banksystem.model;

import org.springframework.format.annotation.DateTimeFormat;
import pl.maciejowsky.banksystem.enums.UserType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class User {

    private int id;


    @NotBlank
    private String fullName;

    @NotNull(message = "You must choose date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    @NotBlank
    private String email;


    @Size(min = 8, max = 24)
    private String password;
    @NotBlank
    private String streetAddress;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;
    @NotBlank
    private String country;
    private Instant createdAt;
    private Instant updatedAt;

    private String authorities;
    private String roles;

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAuthorities() {
        return authorities;
    }

    public String getRoles() {
        return roles;
    }

    public List<String> getRolesList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getAuthoritiesList() {
        if (this.authorities.length() > 0) {
            return Arrays.asList(this.authorities.split(","));
        }
        return new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }


    public User() {
    }

    public User(int id, String fullName, LocalDate birthDay, String email, String password, String streetAddress, String zipCode, String city, String country, Instant createdAt, Instant updatedAt, String authorities, String roles) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.email = email;
        this.password = password;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.authorities = authorities;
        this.roles = roles;
    }

    public User(int id, String fullName, LocalDate birthDay, String email, String password, String streetAddress, String zipCode, String city, String country, Instant createdAt, String authorities, String roles) {
        this.id = id;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.email = email;
        this.password = password;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.createdAt = createdAt;
        this.updatedAt = null;

        this.authorities = authorities;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", authorities='" + authorities + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
