package lookids.user.agreement.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.agreement.vo.in.AgreementUpdateVo;

@Getter
@NoArgsConstructor
public class AgreementUpdateDto {

	private String userUuid;
	private String policyCode;
	private Boolean agree;

	@Builder
	public AgreementUpdateDto(String userUuid, String policyCode, Boolean agree) {
		this.userUuid = userUuid;
		this.policyCode = policyCode;
		this.agree = agree;
	}

	public static AgreementUpdateDto toDto(AgreementUpdateVo agreementUpdateVo) {
		return AgreementUpdateDto.builder()
			.userUuid(agreementUpdateVo.getUserUuid())
			.policyCode(agreementUpdateVo.getPolicyCode())
			.agree(agreementUpdateVo.getAgree())
			.build();
	}
}
