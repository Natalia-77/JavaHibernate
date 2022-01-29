package models;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name="order_id")})
     private Set<Orders> orders=new HashSet<>();

    public Set<Orders> getOrders () {
        return orders;
    }

    public void setOrders(Set<Orders> order) {
        this.orders = order;
    }


}
