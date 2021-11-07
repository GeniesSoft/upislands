package com.geniessoft.backend.utility.mapper;

import com.geniessoft.backend.dto.JetSkiDetailsGetDto;
import com.geniessoft.backend.dto.JetSkiDetailsSaveDto;
import com.geniessoft.backend.dto.JetSkiDetailsUpdateDto;
import com.geniessoft.backend.model.JetSkiDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JetSkiDetailsMapper {

    JetSkiDetailsMapper INSTANCE = Mappers.getMapper(JetSkiDetailsMapper.class);

    JetSkiDetailsGetDto jetSkiDetailsToJetSkiDetailsGetDto(JetSkiDetails jetSkiDetails);

    JetSkiDetails jetSkiDetailsSaveDtoToJetSkiDetails(JetSkiDetailsSaveDto jetSkiDetailsSaveDto);

    JetSkiDetails jetSkiDetailsUpdateDtoToJetSkiDetails(JetSkiDetailsUpdateDto jetSkiDetailsUpdateDto);

}
