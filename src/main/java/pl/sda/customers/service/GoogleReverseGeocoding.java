package pl.sda.customers.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.GeoResult;
import org.springframework.stereotype.Component;
import pl.sda.customers.entity.Address;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GoogleReverseGeocoding implements ReverseGeocoding {

    @NonNull
    private GeoApiContext context;

    @Override
    public Address reverse(double latitude, double longitude) {
        try {
            final var result = GeocodingApi.reverseGeocode(context, new LatLng(latitude, longitude))
                    .await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
