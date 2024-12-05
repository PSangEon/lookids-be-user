package lookids.user.petprofile.dto.out;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.domain.PetProfile;
import lookids.user.petprofile.vo.out.PetProfileResponseVo;

@Getter
@NoArgsConstructor
public class PetProfileResponseDto {

	private String userUuid;
	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;
	private String image;

	@Builder
	public PetProfileResponseDto(String userUuid, String petCode, String name, String comment, String gender,
		LocalDate birthDate, String type, Float weight, String image) {
		this.userUuid = userUuid;
		this.petCode = petCode;
		this.name = name;
		this.comment = comment;
		this.gender = gender;
		this.birthDate = birthDate;
		this.type = type;
		this.weight = weight;
		this.image = image;
	}

	public static PetProfileResponseDto toDto(PetProfile petProfile) {
		return PetProfileResponseDto.builder()
			.userUuid(petProfile.getUserUuid())
			.petCode(petProfile.getPetCode())
			.name(petProfile.getName())
			.comment(petProfile.getComment())
			.gender(petProfile.getGender())
			.birthDate(petProfile.getBirthDate())
			.type(petProfile.getType())
			.weight(petProfile.getWeight())
			.image(petProfile.getImage())
			.build();
	}

	public PetProfileResponseVo toVo() {
		return PetProfileResponseVo.builder()
			.petCode(petCode)
			.name(name)
			.comment(comment)
			.birthDate(birthDate)
			.gender(gender)
			.type(type)
			.weight(weight)
			.image(image)
			.build();
	}
}
