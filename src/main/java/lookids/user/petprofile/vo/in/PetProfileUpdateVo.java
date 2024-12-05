package lookids.user.petprofile.vo.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PetProfileUpdateVo {
	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;
	private String image;
}
