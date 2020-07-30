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
@Table(name = "customer")
public class Customer extends BaseEntity<Integer> {
    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "username", unique = true, length = 50)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email_address", unique = true, length = 50)
    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "customer", orphanRemoval = true)
    private Set<Order> orders;
}
