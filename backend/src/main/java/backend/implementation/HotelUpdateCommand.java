package backend.implementation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelUpdateCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String name;


    @NotBlank
    private String code;

    public HotelUpdateCommand() {}

    public HotelUpdateCommand(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code=code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Long.valueOf(id);
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
}
