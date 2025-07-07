package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDtod) {
        // 1. DTO'dan entity'ye dönüşüm
        Patient patient = patientMapper.toEntity(patientRequestDtod);

        // 2. Entity'yi kaydet (id vs. atanacak)
        patient = patientRepository.save(patient);

        // 3. Kaydedilen entity'yi DTO'ya dönüştür ve döndür
        return patientMapper.toDto(patient);
    }


}
