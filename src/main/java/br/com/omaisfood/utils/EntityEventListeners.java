package br.com.omaisfood.utils;

import br.com.omaisfood.model.Generic;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.util.Date;

public class EntityEventListeners {
    @PrePersist
    public void prePersist(Generic o) {
        o.setCreatedAt(new Date());
        o.setUpdatedAt(new Date());
    }

    @PreUpdate
    public void preUpdate(Generic o) {
        o.setUpdatedAt(new Date());
    }
}
