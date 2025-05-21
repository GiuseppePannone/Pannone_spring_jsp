package com.example.demo.mapper;

import com.example.demo.DTO.DiscenteCorsoListDTO;
import com.example.demo.DTO.DiscenteDTO;
import com.example.demo.entity.Discente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscenteMapper {

    Discente toEntity(DiscenteDTO discenteDTO);

    DiscenteDTO toDto(Discente discente);

    List<DiscenteDTO> entityListToDtoList(List<Discente> discenteList);

    List<Discente> dtoListToEntityList(List<DiscenteDTO> discenteDTOList);

    DiscenteCorsoListDTO toDtoCorsoList(Discente discente);

    List<DiscenteCorsoListDTO> toDtoCorsoList(List<Discente> discente);


}
