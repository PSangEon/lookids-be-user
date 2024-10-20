package lookids.profile.uwerprofile.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.profile.common.entity.BaseResponse;
import lookids.profile.common.entity.BaseResponseStatus;
import lookids.profile.uwerprofile.application.UserProfileService;
import lookids.profile.uwerprofile.dto.in.UserProfileImgDto;
import lookids.profile.uwerprofile.dto.in.UserProfileRequestDto;
import lookids.profile.uwerprofile.dto.in.UserProfileTierDto;
import lookids.profile.uwerprofile.vo.in.UserProfileImgVo;
import lookids.profile.uwerprofile.vo.in.UserProfileRequestVo;
import lookids.profile.uwerprofile.vo.in.UserProfileTierVo;
import lookids.profile.uwerprofile.vo.out.UserProfileResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/userprofile")
public class UserProfileController {

	private final UserProfileService userProfileService;

	@Operation(summary = "readUserProfile API", description = "readUserProfile API 입니다.")
	@GetMapping()
	public BaseResponse<UserProfileResponseVo> readUserProfile(@RequestParam(value = "userUuid") String userUuid) {
		return new BaseResponse<>(userProfileService.readUserProfile(userUuid).toVo());
	}

	@Operation(summary = "createUserProfile API", description = "createUserProfile API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createUserProfile(@RequestBody UserProfileRequestVo userProfileRequestVo) {
		userProfileService.createUserProfile(UserProfileRequestDto.toDto(userProfileRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfile API", description = "updateUserProfile API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updateUserProfile(@RequestBody UserProfileRequestVo userProfileRequestVo) {
		userProfileService.updateUserProfile(UserProfileRequestDto.toDto(userProfileRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfileImg API", description = "updateUserProfileImg API 입니다.")
	@PutMapping("/img")
	public BaseResponse<Void> updateUserProfileImg(@RequestBody UserProfileImgVo userProfileImgVo) {
		userProfileService.updateUserProfileImage(UserProfileImgDto.toDto(userProfileImgVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfileTier API", description = "updateUserProfileTier API 입니다.")
	@PutMapping("/tier")
	public BaseResponse<Void> updateUserProfileTier(@RequestBody UserProfileTierVo userProfileTierVo) {
		userProfileService.updateUserProfileTier(UserProfileTierDto.toDto(userProfileTierVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deleteUserProfile API", description = "deleteUserProfile API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deleteUserProfile(@RequestParam(value = "userUuid") String userUuid) {
		userProfileService.deleteUserProfile(userUuid);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
