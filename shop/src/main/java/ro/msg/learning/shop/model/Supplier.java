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
@Table(name = "supplier")
public class Supplier extends BaseEntity<Integer>
{
    @Column(name = "name", unique = true, length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "supplier", orphanRemoval = true)
    private Set<Product> products;
}
