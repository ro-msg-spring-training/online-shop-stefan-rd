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
@Table(name = "location")
public class Location extends BaseEntity<Integer>
{
    @Column(name  = "name", unique = true, length = 50)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "country", length = 20)),
            @AttributeOverride( name = "city", column = @Column(name = "city", length = 20)),
            @AttributeOverride( name = "county", column = @Column(name = "county", length = 20)),
            @AttributeOverride( name = "streetAddress", column = @Column(name = "street_address", length = 50))
    })
    private Address address;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "location", orphanRemoval = true)
    private Set<Stock> stocks;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "location", orphanRemoval = true)
    private Set<Revenue> revenues;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "shippedFrom", orphanRemoval = true)
    private Set<Order> orders;
}
