package userbase.interfaces;

import userbase.adaptors.models.UserModel;
import userbase.domain.User;
import userbase.adaptors.models.SortingAndOrderArguments;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface Users {

	Optional<User> findById(@NotNull Long id);

	void deleteById(@NotNull Long id);

	int update(@NotNull Long id, @NotBlank String username,  @NotBlank String password, @NotBlank String firstname, @NotBlank String surname);

	Optional<UserModel> findAll(@NotNull SortingAndOrderArguments args);

	Optional<User> findByUsername(String username);

	User getByUsername(String code);
	
	void add(User user);

	User save(@NotBlank String username, @NotBlank String password, @NotBlank String firstname, @NotBlank String surname);
	
	void add(List<User> hotel);
}
