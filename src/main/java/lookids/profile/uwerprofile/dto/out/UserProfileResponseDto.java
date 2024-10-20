package lookids.profile.uwerprofile.dto.out;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.uwerprofile.domain.UserProfile;
import lookids.profile.uwerprofile.vo.out.UserProfileResponseVo;

@Getter
@NoArgsConstructor
public class UserProfileResponseDto {

	private String userUuid;
	private String nickname;
	private String tierCode;
	private LocalDate birthDate;
	private String comment;
	private String gender;
	private String image;

	@Builder
	public UserProfileResponseDto(String userUuid, String nickname, String tierCode, LocalDate birthDate, String gender,
		String comment, String image) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tierCode = tierCode;
		this.birthDate = birthDate;
		this.gender = gender;
		this.comment = comment;
		this.image = image;
	}

	public static UserProfileResponseDto toDto(UserProfile userProfile) {
		return UserProfileResponseDto.builder()
			.userUuid(userProfile.getUserUuid())
			.nickname(userProfile.getNickname())
			.tierCode(userProfile.getTierCode())
			.birthDate(userProfile.getBirthDate())
			.gender(userProfile.getGender())
			.comment(userProfile.getComment())
			.image(userProfile.getImage())
			.build();
	}

	public UserProfileResponseVo toVo() {
		return UserProfileResponseVo.builder()
			.userUuid(userUuid)
			.nickname(nickname)
			.tierCode(tierCode)
			.birthDate(birthDate)
			.gender(gender)
			.comment(comment)
			.image(image)
			.build();
	}
}
