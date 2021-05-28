package com.amperas17.demo3.users.data.user;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserId implements Serializable {

    private UUID uuid;
    private Integer id;

    public UserId() {
    }

    public UserId(UUID uuid, Integer id) {
        this.uuid = uuid;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(uuid, userId.uuid) && Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id);
    }
}
