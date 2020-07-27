package ro.msg.learning.shop.converter;

import ro.msg.learning.shop.dto.AddressDto;
import ro.msg.learning.shop.model.Address;

public class AddressConverter
{
    public static Address convertDtoToAddress(AddressDto dto)
    {
        return Address.builder()
                .streetAddress(dto.getStreetAddress())
                .county(dto.getCounty())
                .city(dto.getCity())
                .country(dto.getCountry())
                .build();
    }

    public static AddressDto convertAddressToDto(Address address)
    {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .county(address.getCounty())
                .streetAddress(address.getStreetAddress())
                .build();
    }
}
