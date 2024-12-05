package lookids.user.petprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.domain.PetProfile;
import lookids.user.petprofile.vo.in.PetProfileUpdateVo;

@Getter
@NoArgsConstructor
public class PetProfileUpdateDto {

	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;
	private String image;

	@Builder
	public PetProfileUpdateDto(String petCode, String name, String comment, String gender, LocalDate birthDate,
		String type, Float weight, String image) {
		this.petCode = petCode;
		this.name = name;
		this.comment = comment;
		this.gender = gender;
		this.birthDate = birthDate;
		this.type = type;
		this.weight = weight;
		this.image = image;
	}

	public static PetProfileUpdateDto toDto(PetProfileUpdateVo petProfileUpdateVo) {
		return PetProfileUpdateDto.builder()
			.petCode(petProfileUpdateVo.getPetCode())
			.name(petProfileUpdateVo.getName())
			.comment(petProfileUpdateVo.getComment())
			.birthDate(petProfileUpdateVo.getBirthDate())
			.type(petProfileUpdateVo.getType())
			.weight(petProfileUpdateVo.getWeight())
			.image(petProfileUpdateVo.getImage())
			.build();
	}

	public PetProfile toEntity(PetProfile petProfile) {
		return PetProfile.builder()
			.id(petProfile.getId())
			.userUuid(petProfile.getUserUuid())
			.petCode(petProfile.getPetCode())
			.name(name)
			.comment(comment)
			.gender(gender)
			.birthDate(birthDate)
			.type(type)
			.weight(weight)
			.image(image)
			.build();
	}
}
