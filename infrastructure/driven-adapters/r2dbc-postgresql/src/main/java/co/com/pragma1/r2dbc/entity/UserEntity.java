package co.com.pragma1.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @Column("user_id")
    private Long id;
    @NonNull
    private String nombres;
    @NonNull
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String direccion;
    private String telefono;
    @NonNull
    private String correoElectronico;
    @NonNull
    private BigDecimal salarioBase;
}
