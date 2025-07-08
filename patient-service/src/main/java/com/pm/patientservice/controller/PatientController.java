package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.dto.validators.Create;
import com.pm.patientservice.dto.validators.Update;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@Tag(name = "Patient Controller", description = "Operations related to patients")
public class PatientController {

    private final PatientService patientService;

    @Operation(
            summary = "Get all patients",
            description = "Retrieve a list of all patients"
    )
    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @Operation(
            summary = "Create patient",
            description = "Create a new patient with the given information"
    )
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(
            @Validated(Create.class) @RequestBody PatientRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(dto));
    }

    @Operation(
            summary = "Update patient",
            description = "Update existing patient details by ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable UUID id,
            @Validated(Update.class) @RequestBody PatientRequestDto dto) {
        return ResponseEntity.ok(patientService.updatePatient(id, dto));
    }

    @Operation(
            summary = "Delete patient",
            description = "Delete patient by ID"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
