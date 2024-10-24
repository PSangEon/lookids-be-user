package lookids.user.tier.presentation;

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
import lookids.user.common.entity.BaseResponse;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.tier.application.TierService;
import lookids.user.tier.dto.in.TierRequestDto;
import lookids.user.tier.dto.out.TierResponseDto;
import lookids.user.tier.vo.in.TierRequestVo;
import lookids.user.tier.vo.out.TierResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tier")
public class TierController {

	private final TierService tierService;

	@Operation(summary = "readTierList API", description = "readTierList API 입니다.")
	@GetMapping("/all")
	public BaseResponse<List<TierResponseVo>> readTierList() {
		List<TierResponseDto> tierResponseDtoList = tierService.readTierList();
		return new BaseResponse<>(tierResponseDtoList.stream().map(TierResponseDto::toVo).toList());
	}

	@Operation(summary = "readTier API", description = "readTier API 입니다.")
	@GetMapping()
	public BaseResponse<TierResponseVo> readTier(@RequestParam(value = "tierCode") String tierCode) {
		return new BaseResponse<>(tierService.readTier(tierCode).toVo());
	}

	@Operation(summary = "createTier API", description = "createTier API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createTier(@RequestBody TierRequestVo tierRequestVo) {
		tierService.createTier(TierRequestDto.toDto(tierRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateTier API", description = "updateTier API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updateTier(@RequestBody TierRequestVo tierRequestVo) {
		tierService.updateTier(TierRequestDto.toDto(tierRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deleteTier API", description = "deleteTier API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deleteTier(@RequestParam(value = "tierCode") String tierCode) {
		tierService.deleteTier(tierCode);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}
}
