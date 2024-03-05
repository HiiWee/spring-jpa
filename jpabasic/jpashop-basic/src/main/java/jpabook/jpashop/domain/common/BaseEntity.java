package jpabook.jpashop.domain.common;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSupgrclass
public abstract class BaseEntity {

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;
}

