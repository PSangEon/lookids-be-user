package lookids.user.userprofile.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponse;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.userprofile.application.UserProfileService;
import lookids.user.userprofile.dto.in.UserProfileImgDto;
import lookids.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.user.userprofile.dto.in.UserProfileTierDto;
import lookids.user.userprofile.dto.in.UserProfileUpdateDto;
import lookids.user.userprofile.vo.in.UserProfileImgVo;
import lookids.user.userprofile.vo.in.UserProfileRequestVo;
import lookids.user.userprofile.vo.in.UserProfileTierVo;
import lookids.user.userprofile.vo.in.UserProfileUpdateVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/write/userprofile")
public class UserProfileWriteController {

	private final UserProfileService userProfileService;

	@Operation(summary = "createUserProfile API", description = "createUserProfile API 입니다.")
	@PostMapping()
	public Boolean createUserProfile(@RequestBody UserProfileRequestVo userProfileRequestVo) {
    		try {
        		userProfileService.createUserProfile(UserProfileRequestDto.toDto(userProfileRequestVo));
	        	return true;  // 성공 시 true 반환
    		} catch (Exception e) {
			return false; // 실패 시 false 반환
    		}
	}

	@Operation(summary = "updateUserProfile API", description = "updateUserProfile API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updateUserProfile(@RequestHeader("uuid") String uuid,
		@RequestBody UserProfileUpdateVo userProfileUpdateVo) {
		userProfileService.updateUserProfile(UserProfileUpdateDto.toDto(userProfileUpdateVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfileImg API", description = "updateUserProfileImg API 입니다.")
	@PutMapping("/img")
	public BaseResponse<Void> updateUserProfileImg(@RequestHeader("uuid") String uuid,
		@RequestBody UserProfileImgVo userProfileImgVo) {
		userProfileService.updateUserProfileImage(UserProfileImgDto.toDto(userProfileImgVo, uuid));
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
	public BaseResponse<Void> deleteUserProfile(@RequestHeader("uuid") String uuid) {
		userProfileService.deleteUserProfile(uuid);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
