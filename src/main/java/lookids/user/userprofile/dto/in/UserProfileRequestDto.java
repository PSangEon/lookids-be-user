package lookids.user.userprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.userprofile.domain.UserProfile;
import lookids.user.userprofile.vo.in.UserProfileRequestVo;

@Getter
@NoArgsConstructor
public class UserProfileRequestDto {

	private String userUuid;
	private String nickname;
	private LocalDate birthDate;
	private String gender;
	private String comment;

	@Builder
	public UserProfileRequestDto(String userUuid, String nickname, LocalDate birthDate, String gender, String comment) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.gender = gender;
		this.comment = comment;
	}

	public static UserProfileRequestDto toDto(UserProfileRequestVo userProfileRequestVo) {
		return UserProfileRequestDto.builder()
			.userUuid(userProfileRequestVo.getUserUuid())
			.nickname(userProfileRequestVo.getNickname())
			.birthDate(userProfileRequestVo.getBirthDate())
			.gender(userProfileRequestVo.getGender())
			.comment(userProfileRequestVo.getComment())
			.build();
	}

	public UserProfile toEntity() {
		return UserProfile.builder()
			.userUuid(userUuid)
			.nickname(nickname)
			.birthDate(birthDate)
			.gender(gender)
			.comment(comment)
			.tierCode("기본 티어")
			.image("기본 이미지")
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
