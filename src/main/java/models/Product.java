package models;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name",length = 100,nullable = false)
    private String name;

    @Column(name="price",nullable = false)
    private double price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="categoty_id")
    private Categories categories=new Categories();

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="tbl_product_orders",
            joinColumns = {@JoinColumn(name="product_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="order_id",referencedColumnName = "id")})
     private List<Orders> orders=new ArrayList<>();

}
