package lookids.profile.uwerprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.uwerprofile.vo.in.UserProfileTierVo;

@Getter
@NoArgsConstructor
public class UserProfileTierDto {

	private String userUuid;
	private String tier;

	@Builder
	public UserProfileTierDto(String userUuid, String tier) {
		this.userUuid = userUuid;
		this.tier = tier;
	}

	public static UserProfileTierDto toDto(UserProfileTierVo userProfileTierVo) {
		return UserProfileTierDto.builder()
			.userUuid(userProfileTierVo.getUserUuid())
			.tier(userProfileTierVo.getTier())
			.build();
	}
}
