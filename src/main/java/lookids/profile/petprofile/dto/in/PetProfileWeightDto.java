package lookids.profile.petprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.petprofile.vo.in.PetProfileWeightVo;

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

	public static PetProfileWeightDto toDto(PetProfileWeightVo petProfileWeightVo) {
		return PetProfileWeightDto.builder()
			.userUuid(petProfileWeightVo.getUserUuid())
			.petUuid(petProfileWeightVo.getPetUuid())
			.weight(petProfileWeightVo.getWeight())
			.build();
	}
}
