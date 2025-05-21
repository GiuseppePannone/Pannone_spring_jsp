package com.example.demo.mapper;

import com.example.demo.DTO.CorsoDTO;
import com.example.demo.entity.Corso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CorsoMapper {

    Corso toEntity(CorsoDTO corso);

    CorsoDTO toDTO(Corso corso);

    List<CorsoDTO> entityListToDTOList(List<Corso> corsoList);

    List<Corso> dtoListToEntityList(List<CorsoDTO> corsoDTOList);


}
