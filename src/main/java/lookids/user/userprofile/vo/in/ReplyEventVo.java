package lookids.user.userprofile.vo.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class ReplyEventVo {
	private String commentCode;
	private String userUuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;

	@Builder
	public ReplyEventVo(String commentCode, String userUuid, String content, LocalDateTime createdAt,
		String parentCommentCode) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
	}
}
