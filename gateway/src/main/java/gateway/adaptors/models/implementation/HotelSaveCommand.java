package gateway.adaptors.models.implementation;

import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Validated backend class -
 * https://docs.oracle.com/javaee/7/api/javax/validation/constraints/package-summary.html
 *
 */

//@Introspected
@Validated // Enable validation of Hotel properties.
public class HotelSaveCommand {

    //(?<![A-Z]{5})(?![A-Z])

    @NotBlank
    @Pattern(regexp = "(?=.*[A-Z]).{2,3}", message = "field_three_char")
    private String code;

    @NotNull
    @NotBlank
    @Size(max = 10, min=3)
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;


    public HotelSaveCommand() {}

    public HotelSaveCommand(String code,String name) {
        this.name = name;
        this.code=code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String name) {
        this.code = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
