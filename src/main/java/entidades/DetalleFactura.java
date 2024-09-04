package entidades;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table (name= "detalle_factura") //Para cambiarle el nombre a la tabla en la base de datos

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class DetalleFactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "cantidad")
    private int cantidad;

    @Column (name = "subtotal")
    private int subtotal;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "fk_articulo")
    private Articulo articulo;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn (name = "fk_factura")
    private Factura factura;
}
