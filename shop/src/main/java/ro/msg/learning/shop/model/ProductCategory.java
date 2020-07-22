package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "productCategory", orphanRemoval = true)
    private Set<Product> products;
}
