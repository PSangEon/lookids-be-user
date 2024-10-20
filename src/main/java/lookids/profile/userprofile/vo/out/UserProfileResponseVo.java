package lookids.profile.userprofile.vo.out;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class UserProfileResponseVo {

	private String userUuid;
	private String nickname;
	private String tierCode;
	private LocalDate birthDate;
	private String gender;
	private String comment;
	private String image;
}