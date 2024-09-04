package entidades;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name= "cliente") //Para cambiarle el nombre a la tabla en la base de datos

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "dni", unique = true) //unique se utiliza para decir que este atributo debe ser unico, no pueden haber 2 iguales
    private int dni;

    //Para cambiarle el nombre a la columna en la base de datos
    @Column (name = "nombre")
    private String nombre;

    @Column (name = "apellido")
    private String apellido;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_domicilio")    //Crea una columna que contiene la clave foranea de Domicilio
    private Domicilio domicilio;

    @OneToMany (mappedBy = "cliente")
    @Builder.Default
    private Set<Factura> facturas = new HashSet<>();
}
