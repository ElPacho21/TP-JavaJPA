package entidades;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name= "factura") //Para cambiarle el nombre a la tabla en la base de datos

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Factura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "fecha")
    private String fecha;

    @Column (name = "numero")
    private int numero;

    @Column (name = "total")
    private double total;

    //Se utiliza el PERSIST en vez del ALL, porque si borro una factura, no quiero que mi cliente se borre de la bd
    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "fk_cliente")
    private Cliente cliente;

    //Si quiero que se Unidereccional
//    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<DetalleFactura> detallesFactura = new HashSet<>();

    @OneToMany (mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<DetalleFactura> detallesFactura = new HashSet<>();
}
