package entidades;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name= "articulo") //Para cambiarle el nombre a la tabla en la base de datos

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "denominacion")
    private String denominacion;

    @Column (name = "cantidad")
    private int cantidad;

    @Column (name = "precio")
    private int precio;

    @OneToMany (mappedBy = "articulo", cascade = CascadeType.PERSIST)
    @Builder.Default
    private Set<DetalleFactura> detallesFactura = new HashSet<>();

    //MERGE sirve para actualizar
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "articulo_categoria",
            joinColumns = @JoinColumn (name = "articulo_id"),
            inverseJoinColumns = @JoinColumn (name = "categoria_id"))
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();

}
