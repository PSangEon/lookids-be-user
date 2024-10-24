package lookids.user.point.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponse;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.point.application.PointService;
import lookids.user.point.dto.in.PointRequestDto;
import lookids.user.point.dto.out.PointResponseDto;
import lookids.user.point.vo.in.PointRequestVo;
import lookids.user.point.vo.out.PointResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/point")
public class PointController {

	private final PointService pointService;

	@Operation(summary = "readPointList API", description = "readPointList API 입니다.")
	@GetMapping()
	public BaseResponse<List<PointResponseVo>> readPointList(@RequestParam(value = "userUuid") String userUuid) {
		List<PointResponseDto> pointResponseDtoList = pointService.readPointList(userUuid);
		return new BaseResponse<>(pointResponseDtoList.stream().map(PointResponseDto::toVo).toList());
	}

	@Operation(summary = "readPointType API", description = "readPointType API 입니다.")
	@GetMapping("/type")
	public BaseResponse<List<PointResponseVo>> readPointType(@RequestParam(value = "userUuid") String userUuid,
		@RequestParam(value = "type") Boolean type) {
		List<PointResponseDto> pointResponseDtoList = pointService.readPointType(userUuid, type);
		return new BaseResponse<>(pointResponseDtoList.stream().map(PointResponseDto::toVo).toList());
	}

	@Operation(summary = "createPoint API", description = "createPoint API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createPoint(@RequestBody PointRequestVo pointRequestVo) {
		pointService.createPoint(PointRequestDto.toDto(pointRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deleteAgreement API", description = "deleteAgreement API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deleteAgreement(@RequestParam(value = "id") Long id,
		@RequestParam(value = "policyUuid") String policyUuid) {
		pointService.deletePoint(id);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
