package co.com.pragma1.api;


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
public class UserDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombres;

    @NotBlank(message = "El apellido son obligatorios")
    private String apellidos;

    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    private String correoElectronico;

    @DecimalMin(value = "0.0", inclusive = true, message = "El salario base debe ser mínimo 0")
    @DecimalMax(value = "15000000.0", inclusive = true, message = "El salario base no puede superar 15,000,000")
    @NotNull(message = "El salario base es obligatorio")
    private BigDecimal salarioBase;

}
