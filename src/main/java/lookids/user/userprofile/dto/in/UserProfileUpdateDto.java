package lookids.user.userprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.userprofile.domain.UserProfile;
import lookids.user.userprofile.vo.in.UserProfileUpdateVo;

@Getter
@NoArgsConstructor
public class UserProfileUpdateDto {

	private String userUuid;
	private String nickname;
	private LocalDate birthDate;
	private String gender;
	private String comment;

	@Builder
	public UserProfileUpdateDto(String userUuid, String nickname, LocalDate birthDate, String gender, String comment) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.comment = comment;
	}

	public static UserProfileUpdateDto toDto(UserProfileUpdateVo userProfileUpdateVo, String userUuid) {
		return UserProfileUpdateDto.builder()
			.userUuid(userUuid)
			.nickname(userProfileUpdateVo.getNickname())
			.birthDate(userProfileUpdateVo.getBirthDate())
			.gender(userProfileUpdateVo.getGender())
			.comment(userProfileUpdateVo.getComment())
			.build();
	}

	public UserProfile toUpdate(UserProfile userProfile) {
		return UserProfile.builder()
			.id(userProfile.getId())
			.userUuid(userProfile.getUserUuid())
			.nickname(nickname)
			.birthDate(birthDate)
			.gender(gender)
			.comment(comment)
			.tierCode(userProfile.getTierCode())
			.image(userProfile.getImage())
			.build();
	}
}
