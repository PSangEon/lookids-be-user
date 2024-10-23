package lookids.user.userprofile.application;

import lookids.user.userprofile.dto.in.UserProfileImgDto;
import lookids.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.user.userprofile.dto.in.UserProfileTierDto;
import lookids.user.userprofile.dto.out.UserProfileResponseDto;

public interface UserProfileService {
	void createUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfileImage(UserProfileImgDto userProfileImgDto);

	void updateUserProfileTier(UserProfileTierDto userProfileTierDto);

	void deleteUserProfile(String userUuid);

	UserProfileResponseDto readUserProfile(String userUuid);
}
