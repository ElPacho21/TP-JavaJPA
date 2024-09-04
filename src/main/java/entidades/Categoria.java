package entidades;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name= "categoria") //Para cambiarle el nombre a la tabla en la base de datos

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "denominacion")
    private String denominacion;

    @ManyToMany (mappedBy = "categorias")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();
}
