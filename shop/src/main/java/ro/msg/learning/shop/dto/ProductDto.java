package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto implements Serializable
{
    private int id;
    private String name;
    private String description;
    private String price;
    private String weight;
    private String imageUrl;
    private int categoryId;
    private int supplierId;
}
