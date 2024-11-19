package lookids.user.petprofile.dto.in;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.domain.PetProfile;
import lookids.user.petprofile.vo.in.PetProfileRequestVo;

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

	public static PetProfileRequestDto toDto(PetProfileRequestVo petProfileRequestVo, String userUuid) {
		return PetProfileRequestDto.builder()
			.userUuid(userUuid)
			.name(petProfileRequestVo.getName())
			.birthDate(petProfileRequestVo.getBirthDate())
			.type(petProfileRequestVo.getType())
			.weight(petProfileRequestVo.getWeight())
			.build();
	}

	public PetProfile toEntity() {
		return PetProfile.builder()
			.userUuid(userUuid)
			.petCode(UUID.randomUUID().toString())
			.name(name)
			.gender(gender)
			.birthDate(birthDate)
			.type(type)
			.weight(weight)
			.image("기본 이미지")
			.build();
	}
}
