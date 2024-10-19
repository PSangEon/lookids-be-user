package lookids.profile.uwerprofile.application;

import lookids.profile.uwerprofile.dto.in.UserProfileImgDto;
import lookids.profile.uwerprofile.dto.in.UserProfileRequestDto;
import lookids.profile.uwerprofile.dto.in.UserProfileTierDto;
import lookids.profile.uwerprofile.dto.out.UserProfileResponseDto;

public interface UserProfileService {
	void createUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfileImage(UserProfileImgDto userProfileImgDto);

	void updateUserProfileTier(UserProfileTierDto userProfileTierDto);

	void deleteUserProfile(String userUuid);

	UserProfileResponseDto readUserProfile(String userUuid);
}
