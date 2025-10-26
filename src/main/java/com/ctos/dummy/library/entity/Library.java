package com.ctos.dummy.library.entity;

import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "library")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "libraryId")
@NamedQuery(
        name = "Library.findByNameLike",
        query = "SELECT lib FROM Library lib WHERE lib.libraryName LIKE :name"
)
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libraryId;

    private String libraryName;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Aisle> aisles;
}
