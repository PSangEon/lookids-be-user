package lookids.profile.petprofile.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PetProfileWeightVo {

	private String userUuid;
	private String petUuid;
	private Float weight;
}
