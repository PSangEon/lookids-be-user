package lookids.profile.userprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.userprofile.vo.in.UserProfileImgVo;

@Getter
@NoArgsConstructor
public class UserProfileImgDto {

	private String userUuid;
	private String image;

	@Builder
	public UserProfileImgDto(String userUuid, String image) {
		this.userUuid = userUuid;
		this.image = image;
	}

	public static UserProfileImgDto toDto(UserProfileImgVo userProfileImgVo) {
		return UserProfileImgDto.builder()
			.userUuid(userProfileImgVo.getUserUuid())
			.image(userProfileImgVo.getImage())
			.build();
	}
}
