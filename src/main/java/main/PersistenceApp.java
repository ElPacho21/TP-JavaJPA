package main;

import entidades.*;

import javax.persistence.*;

public class PersistenceApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin(); //Para comenzar la conexion con la base de datos

            Factura factura1 = Factura.builder()
                    .numero(12)
                    .fecha("10/08/2024")
                    .build();

            Domicilio domicilio1 = Domicilio.builder()
                    .numero(13)
                    .nombreCalle("Av Siempre Viva")
                    .build();

            Cliente cliente1 = Cliente.builder()
                    .dni(45321653)
                    .nombre("Facundo")
                    .apellido("Pacci")
                    .build();

            cliente1.setDomicilio(domicilio1);
            domicilio1.setCliente(cliente1);
            factura1.setCliente(cliente1);

            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogurt Ser Sabor Frutilla")
                    .precio(20)
                    .build();
            Articulo art2 = Articulo.builder()
                    .cantidad(300)
                    .denominacion("Detergente Magistral 500ml")
                    .precio(80)
                    .build();
            Articulo art3 = Articulo.builder()
                    .cantidad(100)
                    .denominacion("Arroz Luchetti")
                    .precio(50)
                    .build();

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);

            art3.getCategorias().add(perecederos);
            perecederos.getArticulos().add(art3);

            DetalleFactura detalle1 = DetalleFactura.builder()
                    .articulo(art1)
                    .cantidad(2)
                    .subtotal(40)
                    .build();
            art1.getDetallesFactura().add(detalle1);
            factura1.getDetallesFactura().add(detalle1);
            detalle1.setFactura(factura1);

            DetalleFactura detalle2 = DetalleFactura.builder()
                    .articulo(art2)
                    .cantidad(1)
                    .subtotal(80)
                    .build();
            art2.getDetallesFactura().add(detalle2);
            factura1.getDetallesFactura().add(detalle2);
            detalle2.setFactura(factura1);

            DetalleFactura detalle3 = DetalleFactura.builder()
                    .articulo(art3)
                    .cantidad(3)
                    .subtotal(150)
                    .build();
            art3.getDetallesFactura().add(detalle3);
            factura1.getDetallesFactura().add(detalle3);
            detalle3.setFactura(factura1);

            factura1.setTotal(270);

            em.persist(factura1);

            em.flush(); //Sirve para limpiar la conexion

            em.getTransaction().commit(); //Se hace el commit del persist

            em.close();
        } catch(Exception e){
            em.getTransaction().rollback(); //Si hay un error se hace un rollback
            e.printStackTrace(System.out);
        }


    }
}
