package lookids.profile.petprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.petprofile.domain.PetProfile;
import lookids.profile.petprofile.vo.in.PetProfileRequestVo;

@Getter
@NoArgsConstructor
public class PetProfileRequestDto {

	private String userUuid;
	private String name;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;

	@Builder
	public PetProfileRequestDto(String userUuid, String name, String gender, LocalDate birthDate, String type,
		Float weight) {
		this.userUuid = userUuid;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.type = type;
		this.weight = weight;
	}

	public static PetProfileRequestDto toDto(PetProfileRequestVo petProfileRequestVo) {
		return PetProfileRequestDto.builder()
			.userUuid(petProfileRequestVo.getUserUuid())
			.name(petProfileRequestVo.getName())
			.birthDate(petProfileRequestVo.getBirthDate())
			.type(petProfileRequestVo.getType())
			.weight(petProfileRequestVo.getWeight())
			.build();
	}

	public PetProfile toEntity() {
		return PetProfile.builder()
			.userUuid(userUuid)
			.name(name)
			.birthDate(birthDate)
			.type(type)
			.weight(weight)
			.build();
	}
}
