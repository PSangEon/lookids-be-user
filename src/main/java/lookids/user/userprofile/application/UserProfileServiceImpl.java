package lookids.user.userprofile.application;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.common.exception.BaseException;
import lookids.user.userprofile.domain.UserProfile;
import lookids.user.userprofile.dto.in.UserProfileImgDto;
import lookids.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.user.userprofile.dto.in.UserProfileTierDto;
import lookids.user.userprofile.dto.out.UserProfileResponseDto;
import lookids.user.userprofile.infrastructure.UserProfileRepository;

@Service
@AllArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;

	@Override
	public void createUserProfile(UserProfileRequestDto userProfileRequestDto) {
		userProfileRepository.save(userProfileRequestDto.toEntity());
	}

	@Override
	public void updateUserProfile(UserProfileRequestDto userProfileRequestDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileRequestDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfile.update(userProfileRequestDto);
		userProfileRepository.save(userProfile);
	}

	@Override
	public void updateUserProfileImage(UserProfileImgDto userProfileImgDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileImgDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfile.updateImage(userProfileImgDto.getImage());
		userProfileRepository.save(userProfile);
	}

	@Override
	public void updateUserProfileTier(UserProfileTierDto userProfileTierDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileTierDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfile.updateTierCode(userProfileTierDto.getTier());
		userProfileRepository.save(userProfile);
	}

	@Override
	public void deleteUserProfile(String userUuid) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.deleteById(userProfile.getId());
	}

	@Override
	public UserProfileResponseDto readUserProfile(String userUuid) {
		return UserProfileResponseDto.toDto(userProfileRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}
}
