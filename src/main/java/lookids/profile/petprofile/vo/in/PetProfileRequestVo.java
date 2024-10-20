package lookids.profile.petprofile.vo.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PetProfileRequestVo {

	private String userUuid;
	private String name;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;
}
