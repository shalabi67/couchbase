package com.couchbase.framework.entity;

import java.util.Date;

public class Entity {
    private String id;
    private Date created;
    private Date updated;
    private long cas;

    @SuppressWarnings("WeakerAccess")
    protected Entity() {
        created = updated = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return getClass().getName();
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public long getCas() {
        return cas;
    }

    public void setCas(long cas) {
        this.cas = cas;
    }

    public String getKey() {
        return getType() + "::" + getId();
    }
}
