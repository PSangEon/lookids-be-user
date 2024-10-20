package lookids.profile.petprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.petprofile.vo.in.PetProfileUpdateVo;

@Getter
@NoArgsConstructor
public class PetProfileUpdateDto {

	private String petUuid;
	private String name;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;

	@Builder
	public PetProfileUpdateDto(String petUuid, String name, String gender, LocalDate birthDate, String type,
		Float weight) {
		this.petUuid = petUuid;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.type = type;
		this.weight = weight;
	}

	public static PetProfileUpdateDto toDto(PetProfileUpdateVo petProfileUpdateVo) {
		return PetProfileUpdateDto.builder()
			.petUuid(petProfileUpdateVo.getPetUuid())
			.name(petProfileUpdateVo.getName())
			.birthDate(petProfileUpdateVo.getBirthDate())
			.type(petProfileUpdateVo.getType())
			.weight(petProfileUpdateVo.getWeight())
			.build();
	}
}
