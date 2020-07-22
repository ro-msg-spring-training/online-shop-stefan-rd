package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "product")
public class Product extends BaseEntity<Integer>
{
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "image_Url", length = 50)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true)
    private Set<Stock> stocks;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

}
