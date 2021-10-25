package com.geniessoft.backend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CompanyLocationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long companyId;
    private Long locationId;
}
