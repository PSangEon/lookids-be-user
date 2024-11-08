package lookids.user.userprofile.vo.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserProfileUpdateVo {

	private String userUuid;
	private String nickname;
	private LocalDate birthDate;
	private String gender;
	private String comment;
}
