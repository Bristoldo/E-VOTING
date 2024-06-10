package com.example.evotingbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "adminBuilder")
@Entity
@Table(name = "admins")
public class Admin extends User {

}
