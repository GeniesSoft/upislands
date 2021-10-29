package com.geniessoft.backend.service;

import com.geniessoft.backend.model.Content;
import org.springframework.stereotype.Service;

public interface ContentService {

    Content saveContent(Content content);
}
