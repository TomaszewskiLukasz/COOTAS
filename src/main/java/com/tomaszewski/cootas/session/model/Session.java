package com.tomaszewski.cootas.session.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant expiresAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SessionFile> sessionFiles = new ArrayList<>();

    protected Session() {}

    public Session(Instant createdAt, Instant expiresAt) {
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public List<SessionFile> getSessionFiles() {
        return Collections.unmodifiableList(sessionFiles);
    }

    public void extendExpiration(Instant newExpiresAt) {
        this.expiresAt = Objects.requireNonNull(newExpiresAt);
    }

    public void addFile(SessionFile file) {
        Objects.requireNonNull(file);
        sessionFiles.add(file);
        file.setSession(this);
    }

    public void removeFile(SessionFile file) {
        if (file != null && sessionFiles.remove(file)) {
            file.setSession(null);
        }
    }
}
