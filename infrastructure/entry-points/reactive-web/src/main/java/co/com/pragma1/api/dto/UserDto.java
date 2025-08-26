package co.com.pragma1.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO que representa un usuario")
public class UserDto {

    @Schema(description = "Nombre del usuario", example = "Juan", required = true)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(description = "Apellido del usuario", example = "Perez", required = true)
    @NotBlank(message = "El apellido son obligatorios")
    private String apellido;

    @Schema(description = "Número de documento del usuario", example = "123456789", required = true)
    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @Schema(description = "Fecha de nacimiento del usuario", example = "1990-05-15")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank(message = "La fecha de nacimiento es obligatorio")
    private LocalDate fechaNacimiento;

    @Schema(description = "Dirección del usuario", example = "Calle 123 #45-67")
    @NotBlank(message = "La dirección es obligatorio")
    private String direccion;

    @Schema(description = "Teléfono de contacto del usuario", example = "3112345678")
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @Schema(description = "Correo electrónico del usuario", example = "juan@mail.com", required = true)
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;

    @Schema(description = "Salario base del usuario", example = "1200000", required = true)
    @DecimalMin(value = "0.0", inclusive = true, message = "El salario base debe ser mínimo 0")
    @DecimalMax(value = "15000000.0", inclusive = true, message = "El salario base no puede superar 15,000,000")
    @NotNull(message = "El salario base es obligatorio")
    private BigDecimal salarioBase;
}
