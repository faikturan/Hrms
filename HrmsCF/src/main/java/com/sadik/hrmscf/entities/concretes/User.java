package com.sadik.hrmscf.entities.concretes;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = {"id"})
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password_hash",length = 2000,nullable = false)
    private String passwordHash;

    @Column(name="created_on",nullable = true)
    private LocalDate createdOn;

    @Column(name="last_modify",nullable = true)
    private LocalDate lastModify;

    @Column(name="is_activate",nullable = false)
    private boolean isActivate;


    @OneToOne(cascade =CascadeType.ALL)
    @JoinTable(name = "user_tokens",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "token_id",referencedColumnName = "id")})
            private Token token;

}
