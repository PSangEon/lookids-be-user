package lookids.profile.uwerprofile.domain;

import java.time.LocalDate;

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
import lookids.profile.uwerprofile.dto.in.UserProfileRequestDto;

@Getter
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("유저 uuid")
	@Column(nullable = false)
	private String userUuid;

	@Comment("유저 닉네임")
	@Column(nullable = false)
	private String nickname;

	@Comment("등급")
	@Column(nullable = false)
	private String tierCode;

	@Comment("유저 생일")
	@Column(nullable = true)
	private LocalDate birthDate;

	@Comment("유저 생일")
	@Column(nullable = true)
	private String gender;

	@Comment("유저 설명")
	@Column(nullable = true)
	private String comment;

	@Comment("유저 이미지")
	@Column(nullable = false)
	private String image;

	@Builder
	public UserProfile(Long id, String userUuid, String nickname, LocalDate birthDate, String gender, String comment) {
		this.id = id;
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tierCode = "기본 티어";
		this.birthDate = birthDate;
		this.gender = gender;
		this.comment = comment;
		this.image = "기본 이미지";
	}

	public void updateTierCode(String tierCode) {
		this.tierCode = tierCode;
	}

	public void updateImage(String image) {
		this.image = image;
	}

	public void update(UserProfileRequestDto userProfileRequestDto) {
		this.nickname = userProfileRequestDto.getNickname();
		this.birthDate = userProfileRequestDto.getBirthDate();
		this.gender = userProfileRequestDto.getGender();
		this.comment = userProfileRequestDto.getComment();
	}
}
