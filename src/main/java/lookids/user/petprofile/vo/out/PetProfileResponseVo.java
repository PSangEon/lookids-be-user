package lookids.user.petprofile.vo.out;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class PetProfileResponseVo {

	private String petCode;
	private String name;
	private String gender;
	private LocalDate birthDate;
	private String type;
	private Float weight;
	private String image;
}
