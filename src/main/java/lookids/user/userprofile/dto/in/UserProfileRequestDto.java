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

	@Builder
	public UserProfileRequestDto(String userUuid, String nickname, LocalDate birthDate, String gender, String comment) {
		this.userUuid = userUuid;
		this.nickname = nickname;
	}

	public static UserProfileRequestDto toDto(UserProfileRequestVo userProfileRequestVo) {
		return UserProfileRequestDto.builder()
			.userUuid(userProfileRequestVo.getUserUuid())
			.nickname(userProfileRequestVo.getNickname())
			.build();
	}

	public UserProfile toEntity() {
		return UserProfile.builder().userUuid(userUuid).nickname(nickname).tierCode("기본 티어").image("기본 이미지").build();
	}
}
