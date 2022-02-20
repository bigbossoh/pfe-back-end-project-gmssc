package com.bzada.gestimowebapi.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
	private static final long serialVersionUID = -7598634368335145470L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CreatedDate
	@Column(name = "creationDate", nullable = false, updatable = false)
	private Instant creationDate;

	@LastModifiedDate
	@Column(name = "lastModifiedDate")
	private Instant lastModifiedDate;

}
