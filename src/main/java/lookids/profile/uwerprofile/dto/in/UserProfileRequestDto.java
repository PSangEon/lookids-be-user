package lookids.profile.uwerprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.uwerprofile.domain.UserProfile;
import lookids.profile.uwerprofile.vo.in.UserProfileRequestVo;

@Getter
@NoArgsConstructor
public class UserProfileRequestDto {

	private String userUuid;
	private String nickname;
	private LocalDate birthDate;
	private String comment;

	@Builder
	public UserProfileRequestDto(String userUuid, String nickname, LocalDate birthDate, String comment) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.comment = comment;
	}

	public static UserProfileRequestDto toDto(UserProfileRequestVo userProfileRequestVo) {
		return UserProfileRequestDto.builder()
			.userUuid(userProfileRequestVo.getUserUuid())
			.nickname(userProfileRequestVo.getNickname())
			.birthDate(userProfileRequestVo.getBirthDate())
			.comment(userProfileRequestVo.getComment())
			.build();
	}

	public UserProfile toEntity() {
		return UserProfile.builder()
			.userUuid(userUuid)
			.nickname(nickname)
			.birthDate(birthDate)
			.comment(comment)
			.build();
	}
}
