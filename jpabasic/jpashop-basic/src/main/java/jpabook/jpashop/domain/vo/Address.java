package jpabook.jpashop.domain.vo;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(final String city, final String street, final String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;

        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) {
            return false;
        }
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) {
            return false;
        }
        return getZipcode() != null ? getZipcode().equals(address.getZipcode()) : address.getZipcode() == null;
    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getZipcode() != null ? getZipcode().hashCode() : 0);
        return result;
    }
}
