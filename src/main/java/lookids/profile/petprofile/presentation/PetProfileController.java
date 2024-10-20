package lookids.profile.petprofile.presentation;

import java.util.List;

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
import lookids.profile.petprofile.application.PetProfileService;
import lookids.profile.petprofile.dto.in.PetProfileImgDto;
import lookids.profile.petprofile.dto.in.PetProfileRequestDto;
import lookids.profile.petprofile.dto.in.PetProfileUpdateDto;
import lookids.profile.petprofile.dto.in.PetProfileWeightDto;
import lookids.profile.petprofile.dto.out.PetProfileResponseDto;
import lookids.profile.petprofile.vo.in.PetProfileImgVo;
import lookids.profile.petprofile.vo.in.PetProfileRequestVo;
import lookids.profile.petprofile.vo.in.PetProfileUpdateVo;
import lookids.profile.petprofile.vo.in.PetProfileWeightVo;
import lookids.profile.petprofile.vo.out.PetProfileResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/petprofile")
public class PetProfileController {

	private final PetProfileService petProfileService;

	@Operation(summary = "readPetProfileList API", description = "readPetProfileList API 입니다.")
	@GetMapping("/all")
	public BaseResponse<List<PetProfileResponseVo>> readPetProfileList(
		@RequestParam(value = "userUuid") String userUuid) {
		List<PetProfileResponseDto> petProfileResponseDtoList = petProfileService.readPetProfileList(userUuid);
		return new BaseResponse<>(petProfileResponseDtoList.stream().map(PetProfileResponseDto::toVo).toList());
	}

	@Operation(summary = "readPetProfile API", description = "readPetProfile API 입니다.")
	@GetMapping()
	public BaseResponse<PetProfileResponseVo> readPetProfile(@RequestParam(value = "petUuid") String petUuid) {
		return new BaseResponse<>(petProfileService.readPetProfile(petUuid).toVo());
	}

	@Operation(summary = "createPetProfile API", description = "createPetProfile API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createPetProfile(@RequestBody PetProfileRequestVo petProfileRequestVo) {
		petProfileService.createPetProfile(PetProfileRequestDto.toDto(petProfileRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePetProfile API", description = "updatePetProfile API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updatePetProfile(@RequestBody PetProfileUpdateVo petProfileUpdateVo) {
		petProfileService.updatePetProfile(PetProfileUpdateDto.toDto(petProfileUpdateVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePetProfileImg API", description = "updatePetProfileImg API 입니다.")
	@PutMapping("/img")
	public BaseResponse<Void> updatePetProfileImg(@RequestBody PetProfileImgVo petProfileImgVo) {
		petProfileService.updatePetProfileImage(PetProfileImgDto.toDto(petProfileImgVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePetProfileWeight API", description = "updatePetProfileWeight API 입니다.")
	@PutMapping("/weight")
	public BaseResponse<Void> updatePetProfileWeight(@RequestBody PetProfileWeightVo petProfileWeightVo) {
		petProfileService.updatePetProfileWeight(PetProfileWeightDto.toDto(petProfileWeightVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deletePetProfile API", description = "deletePetProfile API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deletePetProfile(@RequestParam(value = "petUuid") String petUuid) {
		petProfileService.deletePetProfile(petUuid);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
