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
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity<Integer> {
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order correspondingOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
