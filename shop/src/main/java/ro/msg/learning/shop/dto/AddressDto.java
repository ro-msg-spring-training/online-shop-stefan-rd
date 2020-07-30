package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDto implements Serializable {
    private String country;
    private String county;
    private String city;
    private String streetAddress;

    public static Address convertDtoToAddress(AddressDto dto) {
        return Address.builder()
                .streetAddress(dto.getStreetAddress())
                .county(dto.getCounty())
                .city(dto.getCity())
                .country(dto.getCountry())
                .build();
    }

    public static AddressDto convertAddressToDto(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .county(address.getCounty())
                .streetAddress(address.getStreetAddress())
                .build();
    }
}
