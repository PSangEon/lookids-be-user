package lookids.profile.petprofile.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.profile.petprofile.dto.in.PetProfileUpdateDto;

@Getter
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
@Entity
public class PetProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("유저 uuid")
	@Column(nullable = false)
	private String userUuid;

	@Comment("반려동물 uuid")
	@Column(nullable = false)
	private String petUuid;

	@Comment("반려동물 이름")
	@Column(nullable = false)
	private String name;

	@Comment("반려동물 성별")
	@Column(nullable = true)
	private String gender;

	@Comment("반려동물 생일")
	@Column(nullable = true)
	private LocalDate birthDate;

	@Comment("반려동물 종")
	@Column(nullable = true)
	private String type;

	@Comment("반려동물 무게")
	@Column(nullable = true)
	private Float weight;

	@Comment("반려동물 이미지")
	@Column(nullable = false)
	private String image;

	@Builder
	public PetProfile(Long id, String userUuid, String name, String gender, LocalDate birthDate, String type,
		Float weight) {
		this.id = id;
		this.userUuid = userUuid;
		this.petUuid = UUID.randomUUID().toString();
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.type = type;
		this.weight = weight;
		this.image = "기본 이미지";
	}

	public void updateImage(String image) {
		this.image = image;
	}

	public void updateWeight(Float weight) {
		this.weight = weight;
	}

	public void update(PetProfileUpdateDto petProfileUpdateDto) {
		this.name = petProfileUpdateDto.getName();
		this.gender = petProfileUpdateDto.getGender();
		this.birthDate = petProfileUpdateDto.getBirthDate();
		this.type = petProfileUpdateDto.getType();
	}
}
