package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")

public interface PatientMapper {

    @Mapping(target = "id", ignore = true) // Yeni kayıt için id otomatik atanıyor
    @Mapping(target = "dateOfBirth", expression = "java(java.time.LocalDate.parse(patientRequestDto.getDateOfBirth()))")
    @Mapping(target = "registeredDate", expression = "java(java.time.LocalDate.parse(patientRequestDto.getRegisteredDate()))")
    Patient toEntity(PatientRequestDto patientRequestDto);

    @Mapping(target = "id", expression = "java(patient.getId().toString())")
    @Mapping(target = "dateOfBirth", expression = "java(patient.getDateOfBirth().toString())")
    PatientResponseDto toDto(Patient patient);


    // Update methodu: dto verisiyle entity'yi günceller
    @Mapping(target = "id", ignore = true)  // id değişmesin
    @Mapping(target = "registeredDate", ignore = true) // istersen bu alanı güncellemeyi atla
    @Mapping(target = "dateOfBirth", expression = "java(java.time.LocalDate.parse(patientRequestDto.getDateOfBirth()))")
    void updatePatientFromDto(PatientRequestDto patientRequestDto, @MappingTarget Patient patient);

}
