package com.topjava.graduationproject.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class User {
    private Long id;
    private String name;
    private String passSalt;
    private String passHash;
    private String role;
    private Long alreadyVotedFor;
    private LocalDateTime latestVote;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PASS_SALT")
    public String getPassSalt() {
        return passSalt;
    }

    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }

    @Basic
    @Column(name = "PASS_HASH")
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @Basic
    @Column(name = "ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "ALREADY_VOTED_FOR")
    public Long getAlreadyVotedFor() {
        return alreadyVotedFor;
    }

    public void setAlreadyVotedFor(Long alreadyVotedFor) {
        this.alreadyVotedFor = alreadyVotedFor;
    }

    @Basic
    @Column(name = "LATEST_VOTE")
    public LocalDateTime getLatestVote() {
        return latestVote;
    }

    public void setLatestVote(LocalDateTime latestVote) {
        this.latestVote = latestVote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(passSalt, user.passSalt) &&
                Objects.equals(passHash, user.passHash) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, passSalt, passHash, role);
    }
}
