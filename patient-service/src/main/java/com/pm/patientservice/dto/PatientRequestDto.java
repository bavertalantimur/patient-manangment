package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validators.Create;
import com.pm.patientservice.dto.validators.Update;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data

public class PatientRequestDto {
    @NotBlank(message = "Name is required", groups = Create.class)
    @Size(max = 100, message = "Name cannot exceed 100 characters", groups = {Create.class, Update.class})
    private String name;

    @NotBlank(message = "Email is required", groups = Create.class)
    @Email(message = "Email should be valid", groups = {Create.class, Update.class})
    private String email;

    @NotBlank(message = "Address is required", groups = Create.class)
    private String address;

    @NotBlank(message = "Date of birth is required", groups = Create.class)
    private String dateOfBirth;

    @NotBlank(message = "Registered date is required", groups = Create.class)
    private String registeredDate;


}
