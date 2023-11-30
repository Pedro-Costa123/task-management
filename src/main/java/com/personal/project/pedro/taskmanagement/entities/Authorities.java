package com.personal.project.pedro.taskmanagement.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "authority")
    private String authority;

    public Authorities() {
    }

    public Authorities(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorities that = (Authorities) o;
        return Objects.equals(id, that.id) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }
}
