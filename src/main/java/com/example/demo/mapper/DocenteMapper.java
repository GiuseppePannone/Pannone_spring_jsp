package com.example.demo.mapper;

import com.example.demo.DTO.DocenteCorsoListDTO;
import com.example.demo.DTO.DocenteDTO;
import com.example.demo.entity.Docente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    Docente toEntity(DocenteDTO docenteDTO);

    DocenteDTO toDto(Docente docente);

    List<DocenteDTO> entityListToDtoList(List<Docente> docenti);

    List<Docente> dtoListToEntityList(List<DocenteDTO> docenteDTO);

    DocenteCorsoListDTO toCorsoListDTO(Docente docente);
}
