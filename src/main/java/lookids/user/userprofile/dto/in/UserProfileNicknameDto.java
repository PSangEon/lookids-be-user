package lookids.user.userprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.userprofile.domain.UserProfile;
import lookids.user.userprofile.vo.in.UserProfileNicknameVo;

@Getter
@NoArgsConstructor
public class UserProfileNicknameDto {

	private String userUuid;
	private String nickname;

	@Builder
	public UserProfileNicknameDto(String userUuid, String nickname) {
		this.userUuid = userUuid;
		this.nickname = nickname;
	}

	public static UserProfileNicknameDto toDto(UserProfileNicknameVo userProfileNicknameVo, String userUuid) {
		return UserProfileNicknameDto.builder()
			.userUuid(userUuid)
			.nickname(userProfileNicknameVo.getNickname())
			.build();
	}

	public UserProfile toEntity(UserProfile userProfile, String tag) {
		return UserProfile.builder()
			.id(userProfile.getId())
			.userUuid(userProfile.getUserUuid())
			.nickname(nickname)
			.tag(tag)
			.birthDate(userProfile.getBirthDate())
			.gender(userProfile.getGender())
			.comment(userProfile.getComment())
			.tierCode(userProfile.getTierCode())
			.image(userProfile.getImage())
			.build();
	}
}
