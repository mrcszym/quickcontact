package com.quickcontact.quickcontact.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ROLES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<User> users;

}
