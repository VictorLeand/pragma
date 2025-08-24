package co.com.pragma1.model.user;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

   private Long id;
   private String nombres;
   private String apellidos;
   private LocalDate fechaNacimiento;
   private String direccion;
   private String telefono;
   private String correoElectronico;
   private BigDecimal salarioBase;
}
