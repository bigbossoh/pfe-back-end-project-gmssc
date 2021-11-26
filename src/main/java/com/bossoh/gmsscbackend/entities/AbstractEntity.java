package com.bossoh.gmsscbackend.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
	private static final long serialVersionUID = -7598634368335145470L;
	@Id
	@GeneratedValue
	private Long id;
	@CreatedDate
	@Column(name = "creationDate", nullable = false, updatable = false)
	private Instant creationDate;

	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private Instant lastModifiedDate;

}
