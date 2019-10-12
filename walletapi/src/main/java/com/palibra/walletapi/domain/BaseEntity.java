package com.palibra.walletapi.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @NotNull
    @CreationTimestamp
    private LocalDateTime regDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updDate;
}