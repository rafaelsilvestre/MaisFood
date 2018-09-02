package br.com.omaisfood.model;

import br.com.omaisfood.utils.EntityEventListeners;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@EntityListeners({
    EntityEventListeners.class
})
public abstract class Generic {

    public abstract void setCreatedAt(LocalDateTime createdAt);

    public abstract void setUpdatedAt(LocalDateTime updatedAt);
}
