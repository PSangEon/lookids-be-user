package lookids.user.userprofile.vo.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentEventVo {
	private String commentCode;
	private String feedCode;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
}
