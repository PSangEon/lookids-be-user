package lookids.user.userprofile.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponse;
import lookids.user.userprofile.application.UserProfileService;
import lookids.user.userprofile.vo.out.UserProfileResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/userprofile/read")
public class UserProfileReadController {

	private final UserProfileService userProfileService;

	@Operation(summary = "readUserProfile API", description = "readUserProfile API 입니다.")
	@GetMapping()
	public BaseResponse<UserProfileResponseVo> readUserProfile(@RequestParam(value = "userUuid") String userUuid) {
		return new BaseResponse<>(userProfileService.readUserProfile(userUuid).toVo());
	}
}
