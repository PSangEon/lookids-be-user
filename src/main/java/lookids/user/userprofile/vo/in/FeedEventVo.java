package lookids.user.userprofile.vo.in;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeedEventVo {
	private String feedCode;
	private String uuid;
	private String petCode;
	private String content;
	private List<String> tags;
	private boolean state;
	private List<String> mediaUrl;
	private LocalDateTime createdAt;
}
