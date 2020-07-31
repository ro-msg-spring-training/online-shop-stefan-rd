package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Entity
@Table(name = "customer_order")
public class Order extends BaseEntity<Integer> {
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "country", length = 20)),
            @AttributeOverride(name = "city", column = @Column(name = "city", length = 20)),
            @AttributeOverride(name = "county", column = @Column(name = "county", length = 20)),
            @AttributeOverride(name = "streetAddress", column = @Column(name = "street_address", length = 50))
    })
    private Address address;

    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location shippedFrom;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "correspondingOrder", orphanRemoval = true)
    private Set<OrderDetail> orderDetails;

    public void addDetail(OrderDetail detail) {
        if (this.orderDetails == null) {
            this.orderDetails = new HashSet<>();
            this.orderDetails.add(detail);
        } else {
            this.orderDetails.add(detail);
        }
    }

}
