package lookids.profile.userprofile.application;

import lookids.profile.userprofile.dto.in.UserProfileImgDto;
import lookids.profile.userprofile.dto.in.UserProfileRequestDto;
import lookids.profile.userprofile.dto.in.UserProfileTierDto;
import lookids.profile.userprofile.dto.out.UserProfileResponseDto;

public interface UserProfileService {
	void createUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfileImage(UserProfileImgDto userProfileImgDto);

	void updateUserProfileTier(UserProfileTierDto userProfileTierDto);

	void deleteUserProfile(String userUuid);

	UserProfileResponseDto readUserProfile(String userUuid);
}
