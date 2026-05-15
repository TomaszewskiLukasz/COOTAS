package com.tomaszewski.cootas.session.model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "files")
public class SessionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Column(nullable = false)
    private String fileName;

    @Lob
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private Instant uploadedAt;

    @Column(nullable = false)
    private String uploadedBy;

    protected SessionFile() {}

    public SessionFile(Session session, String fileName, byte[] content, String mimeType, long size, Instant uploadedAt, String uploadedBy) {
        this.session = session;
        this.fileName = fileName;
        this.content = content;
        this.mimeType = mimeType;
        this.size = size;
        this.uploadedAt = uploadedAt;
        this.uploadedBy = uploadedBy;
    }

    public UUID getId() {
        return id;
    }

    public Session getSession() {
        return session;
    }

    protected void setSession(Session session) {
        this.session = session;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getContent() {
        Objects.requireNonNull(content);
        return content.clone();
    }

    public String getMimeType() {
        return mimeType;
    }

    public long getSize() {
        return size;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }
}
