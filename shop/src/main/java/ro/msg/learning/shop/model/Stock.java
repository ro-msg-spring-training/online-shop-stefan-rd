package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "stock")
public class Stock extends BaseEntity<Integer>
{
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

}
