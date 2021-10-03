package pl.sda.customers.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.customers.entity.Address;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoogleReverseGeocodingTest {

    @Autowired
    private  GoogleReverseGeocoding reverseGeocoding;

    @Test
    void shouldFindAddressForCoordinates(){
        // when
        final var address = reverseGeocoding.reverse(52.242799,20.979061);

        //then
        assertNotNull(address);
        assertEquals("Dzielna 21", address.getStreet());
        assertEquals("Warszawa", address.getCity());
        assertEquals("00-162", address.getZipCode());
        assertEquals("PL", address.getCountryCode());
    }









}