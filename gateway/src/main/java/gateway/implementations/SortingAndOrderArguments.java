package gateway.implementations;


import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import java.util.Optional;

public class SortingAndOrderArguments {

    @Nullable
    private String name;

    @Nullable
    private Integer offset;

    @Nullable
    private Integer max;

    @Nullable
    @Pattern(regexp = "id|name|code|lastUpdated|phone|email")
    private String sort;

    @Pattern(regexp = "asc|ASC|desc|DESC")
    @Nullable
    private String order;

    public SortingAndOrderArguments() {

    }


    public Optional<Integer> getOffset() {
        if(offset == null) {
           // return Optional.empty();
            return Optional.of(0);
        }
        return Optional.of(offset);
    }


    public void setOffset(@Nullable Optional<Integer>  offset) {
        //if (offset.get() ==null) {
         //   this.offset=0;
        //} else {
            this.offset = Integer.valueOf(offset.get());
        //}
    }



    public Optional<Integer> getMax() {
        if(max == null) {
            return Optional.of(10);
        }
        return Optional.of(max);
    }


    /*

       public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public void setOffset(String offset) {

        this.offset = Integer.valueOf(offset);

    }
    public void setMax(String max) {

            this.max = Integer.valueOf(max);

    }
    public void setMax(@Nullable  Optional<Integer> max) {
        System.out.println("Setting max");
        if (max.get() ==null) {
            this.max=0;
        } else {
            this.max = max.get();
        }
    }

    */
    public void setMax(@Nullable Integer max) {
        this.max = max;
    }





    public Optional<String> getName() {
        if(name == null) {
            return Optional.empty();
        }
        return Optional.of(name);
    }

    public void setName(@Nullable String name) {
        System.out.println("Setting name"+name);
        if (name==null||name=="") {
            this.name = null;
        } else {
            this.name = name;
        }

    }


    public Optional<String> getSort() {
        if(sort == null) {
            return Optional.of("id");
        }
        return Optional.of(sort);
    }

    public void setSort(@Nullable String sort) {
        if (sort==null) {
            sort="asc";
        }
        this.sort = sort;
    }

    public Optional<String> getOrder() {
        if(order == null) {
            return Optional.of("asc");
        }
        return Optional.of(order);
    }

    public void setOrder(@Nullable String order) {
        if (order==null) {
            order="id";
        }
        this.order = order;
    }
}