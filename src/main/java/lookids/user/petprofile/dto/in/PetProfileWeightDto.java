package lookids.user.petprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.domain.PetProfile;
import lookids.user.petprofile.vo.in.PetProfileWeightVo;

@Getter
@NoArgsConstructor
public class PetProfileWeightDto {

	private String userUuid;
	private String petUuid;
	private Float weight;

	@Builder
	public PetProfileWeightDto(String userUuid, String petUuid, Float weight) {
		this.userUuid = userUuid;
		this.petUuid = petUuid;
		this.weight = weight;
	}

	public static PetProfileWeightDto toDto(PetProfileWeightVo petProfileWeightVo, String userUuid) {
		return PetProfileWeightDto.builder()
			.userUuid(userUuid)
			.petUuid(petProfileWeightVo.getPetUuid())
			.weight(petProfileWeightVo.getWeight())
			.build();
	}

	public PetProfile toEntity(PetProfile petProfile) {
		return PetProfile.builder()
			.id(petProfile.getId())
			.userUuid(petProfile.getUserUuid())
			.name(petProfile.getName())
			.birthDate(petProfile.getBirthDate())
			.type(petProfile.getType())
			.weight(weight)
			.image(petProfile.getImage())
			.build();
	}
}
