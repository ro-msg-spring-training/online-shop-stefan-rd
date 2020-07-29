package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Entity

@Table(name = "product_category")
public class ProductCategory extends BaseEntity<Integer>
{
    @Column(name = "name", unique = true, length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "productCategory", orphanRemoval = true)
    private Set<Product> products;

    public void addProduct(Product product)
    {
        if(this.products == null)
        {
            this.products = new HashSet<>();
            this.products.add(product);
        }
        else
        {
            this.products.add(product);
        }
    }

    public void removeProduct(Product product)
    {
        this.products.remove(product);
    }
}
