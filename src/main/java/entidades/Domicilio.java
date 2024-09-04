package entidades;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table (name= "domicilio") //Para cambiarle el nombre a la tabla en la base de datos

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Domicilio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "nombre_calle")
    private String nombreCalle;

    @Column (name = "numero")
    private int numero;

    @OneToOne (mappedBy = "domicilio")
    private Cliente cliente;

}
