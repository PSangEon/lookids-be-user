package lookids.user.agreement.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AgreementUpdateVo {
	private String userUuid;
	private String policyCode;
	private Boolean agree;
}
