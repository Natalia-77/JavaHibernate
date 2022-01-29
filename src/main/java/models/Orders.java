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
@Table(name = "tbl_orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "number")
    private int number;
    //@ManyToMany(mappedBy = "orders")
    @ManyToMany
    @JoinTable(name="tbl_product_orders",
            joinColumns = {@JoinColumn(name="order_id")},
            inverseJoinColumns = {@JoinColumn(name="product_id")})
    private Set<Product> productList = new HashSet<>();
    public Set<Product> getProductSet() {
        return productList;
    }
    public void setProductSet(Set<Product> products) {
        this.productList = products;
    }
    public void add(Product product) {
        productList.add(product);
    }
}
