package africa.semicolon.dto.responses;

import lombok.Data;

@Data
public class LoginUserResponse {
    private String email;
    private String statusMessage;
}
