package br.com.omaisfood.utils;

import br.com.omaisfood.model.Generic;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.Date;

public class EntityEventListeners {
    @PrePersist
    public void prePersist(Generic o) {
        o.setCreatedAt(LocalDateTime.now());
        o.setUpdatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Generic o) {
        o.setUpdatedAt(LocalDateTime.now());
    }
}
