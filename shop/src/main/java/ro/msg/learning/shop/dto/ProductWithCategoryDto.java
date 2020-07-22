package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductWithCategoryDto
{
    private int id;
    private String name;
    private String description;
    private String price;
    private String weight;
    private String imageUrl;
    private String categoryName;
    private String categoryDescription;

}
