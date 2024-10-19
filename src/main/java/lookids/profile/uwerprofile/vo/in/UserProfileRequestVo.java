package lookids.profile.uwerprofile.vo.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class UserProfileRequestVo {

	private String userUuid;
	private String nickname;
	private LocalDate birthDate;
	private String comment;
}
