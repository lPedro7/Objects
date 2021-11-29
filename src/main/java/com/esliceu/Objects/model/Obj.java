package com.esliceu.Objects.model;

import java.util.Date;

public class Obj {
    private String name;
    private String uri;
    private String bucketUri;
    private String username_owner;
    private byte[] content;
    private int version;
    private int contentLength;
    private String contentType;
    private Date lastModified;
    private Date createdDate;
    private String hash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUsername_owner() {
        return username_owner;
    }

    public void setUsername_owner(String username_owner) {
        this.username_owner = username_owner;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    public String getBucketUri() {
        return bucketUri;
    }

    public void setBucketUri(String bucketUri) {
        this.bucketUri = bucketUri;
    }
}
