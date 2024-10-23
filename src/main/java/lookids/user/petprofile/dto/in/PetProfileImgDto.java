package lookids.user.petprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.vo.in.PetProfileImgVo;

@Getter
@NoArgsConstructor
public class PetProfileImgDto {

	private String userUuid;
	private String petUuid;
	private String image;

	@Builder
	public PetProfileImgDto(String userUuid, String petUuid, String image) {
		this.userUuid = userUuid;
		this.petUuid = petUuid;
		this.image = image;
	}

	public static PetProfileImgDto toDto(PetProfileImgVo petProfileImgVo) {
		return PetProfileImgDto.builder()
			.userUuid(petProfileImgVo.getUserUuid())
			.petUuid(petProfileImgVo.getPetUuid())
			.image(petProfileImgVo.getImage())
			.build();
	}
}
