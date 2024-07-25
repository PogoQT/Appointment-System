package com.example.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    private String review;

    @ManyToOne
    @JoinColumn(name = "providerID")
    private Provider provider;

    @Column(name = "userID")
    private int userID;

    @ManyToOne
    @JoinColumn(name = "userID" , referencedColumnName = "userID", insertable = false, updatable = false)
    private User user;
}