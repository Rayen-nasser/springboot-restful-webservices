package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Data Transfer Object representing a User, used for creating and updating user information."
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Schema(
            description = "Unique identifier for the user.",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Last name of the user.",
            example = "Doe"
    )
    @NotEmpty(message = "Last name must not be empty.")
    private String lastName;

    @Schema(
            description = "First name of the user.",
            example = "John"
    )
    @NotEmpty(message = "First name must not be empty.")
    private String firstName;

    @Schema(
            description = "Email address of the user. Must be a valid email format.",
            example = "john.doe@example.com"
    )
    @NotEmpty(message = "Email must not be empty.")
    @Email(message = "Email should be valid.")
    private String email;
}
