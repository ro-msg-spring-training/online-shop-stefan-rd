package ro.msg.learning.shop.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto implements Serializable
{
    private int id;
    private int locationId;
    private int customerId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private String createdAt;

    private AddressDto address;
}
