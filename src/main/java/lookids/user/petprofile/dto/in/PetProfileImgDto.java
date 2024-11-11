package lookids.user.petprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.domain.PetProfile;
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

	public static PetProfileImgDto toDto(PetProfileImgVo petProfileImgVo, String userUuid) {
		return PetProfileImgDto.builder()
			.userUuid(userUuid)
			.petUuid(petProfileImgVo.getPetUuid())
			.image(petProfileImgVo.getImage())
			.build();
	}

	public PetProfile toEntity(PetProfile petProfile) {
		return PetProfile.builder()
			.id(petProfile.getId())
			.userUuid(petProfile.getUserUuid())
			.name(petProfile.getName())
			.birthDate(petProfile.getBirthDate())
			.type(petProfile.getType())
			.weight(petProfile.getWeight())
			.image(image)
			.build();
	}
}
