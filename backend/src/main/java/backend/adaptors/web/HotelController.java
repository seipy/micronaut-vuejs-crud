package backend.adaptors.web;

import backend.adaptors.models.HotelModel;
import backend.domain.Hotel;
import backend.domain.interfaces.Hotels;
import backend.implementation.HotelSaveCommand;
import backend.implementation.HotelUpdateCommand;
import backend.implementation.SortingAndOrderArguments;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import io.reactivex.annotations.Nullable;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Validated
@Controller("/")
public class HotelController {

    protected final Hotels hotels;

    public HotelController(Hotels hotels) {
        this.hotels = hotels;
    }


    @Get("/status")
    public HttpResponse status() {
        return HttpResponse.ok();
    }


    @Get("/{id}")
    public Hotel show(Long id) {
        return hotels
                .findById(id)
                .orElse(null);
    }

    //, consumes = MediaType.APPLICATION_FORM_URLENCODED
    @Put("/update/{id}")
    public HttpResponse update(Long id,@Body @Valid HotelUpdateCommand command) {
        System.out.println(" In controller updateHotel");
        int numberOfEntitiesUpdated = hotels.update(id, command.getName(),command.getCode(), command.getPhone(), command.getEmail());

        return HttpResponse
                .noContent()
                .header(HttpHeaders.LOCATION, location(command.getId()).getPath());
    }

    /*
    @Get("/list2{?args*}")
    public List<Hotel> list(@Valid SortingAndOrderArguments args) {
        return hotels.findAll(args);
    }



    public List<Hotel> findAll(@Nullable Integer max, @Nullable Integer offset, @Nullable String order) {
        SortingAndOrderArguments args = new SortingAndOrderArguments();
        args.setMax(max);
        args.setOffset(offset);
        args.setOrder(order);
        return hotels.findAll(args);
    }
    */

    @Get("/list{?args*}") //, consumes = MediaType.APPLICATION_JSON
    public Optional<HotelModel> findAll(@Nullable SortingAndOrderArguments args) {
        return hotels.findAll(args);
    }

    @Post("/")
    public HttpResponse<Hotel> save(@Body @Valid HotelSaveCommand cmd) {
        Hotel hotel = hotels.save(cmd.getCode(),cmd.getName());

        return HttpResponse
                .created(hotel)
                .headers(headers -> headers.location(location(hotel.getId())));
    }

    @Delete("/{id}")
    public HttpResponse delete(Long id) {
        hotels.deleteById(id);
        return HttpResponse.ok();
    }

    protected URI location(Long id) {
        return URI.create("/" + id);
    }

    protected URI location(Hotel hotel) {
        return location(hotel.getId());
    }

}
