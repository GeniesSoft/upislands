package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Company;
import com.geniessoft.backend.model.CompanyContent;
import com.geniessoft.backend.model.Location;
import com.geniessoft.backend.model.LocationContent;
import org.springframework.stereotype.Service;

@Service
public interface LocationContentService {

    LocationContent saveLocationContent(
            String fileName,
            String path,
            String contentType,
            String contentText,
            Location location);
}
