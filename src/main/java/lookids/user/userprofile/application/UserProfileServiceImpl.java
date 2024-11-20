package lookids.user.userprofile.application;

import java.util.Random;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.common.exception.BaseException;
import lookids.user.userprofile.domain.UserProfile;
import lookids.user.userprofile.dto.in.UserProfileImgDto;
import lookids.user.userprofile.dto.in.UserProfileNicknameDto;
import lookids.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.user.userprofile.dto.in.UserProfileTierDto;
import lookids.user.userprofile.dto.in.UserProfileUpdateDto;
import lookids.user.userprofile.dto.out.UserProfileKafkaDto;
import lookids.user.userprofile.dto.out.UserProfileResponseDto;
import lookids.user.userprofile.infrastructure.UserProfileRepository;
import lookids.user.userprofile.vo.in.CommentEventVo;
import lookids.user.userprofile.vo.in.FeedEventVo;
import lookids.user.userprofile.vo.out.UserProfileKafkaVo;

@Service
@AllArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;

	@Override
	public void createUserProfile(UserProfileRequestDto userProfileRequestDto) {
		String tag;
		int maxAttempts = 5;  // 최대 시도 횟수
		int attempt = 0;

		do {
			tag = generateRandomTag();
			attempt++;

			if (attempt >= maxAttempts) {
				throw new BaseException(BaseResponseStatus.DUPLICATED_TAG);
			}
		} while (userProfileRepository.findByNicknameAndTag(userProfileRequestDto.getNickname(), tag).isPresent());

		userProfileRepository.save(userProfileRequestDto.toEntity(tag));
	}

	@Override
	public void updateUserProfile(UserProfileUpdateDto userProfileUpdateDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileUpdateDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.save(userProfileUpdateDto.toUpdate(userProfile));
	}

	@Override
	public void updateUserProfileImage(UserProfileImgDto userProfileImgDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileImgDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.save(userProfileImgDto.toEntity(userProfile));
	}

	@Override
	public void updateUserProfileTier(UserProfileTierDto userProfileTierDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileTierDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.save(userProfileTierDto.toEntity(userProfile));
	}

	@Override
	public void deleteUserProfile(String userUuid) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.deleteById(userProfile.getId());
	}

	@Override
	public void updateUserProfileNickname(UserProfileNicknameDto userProfileNicknameDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileNicknameDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		String tag;
		int maxAttempts = 5;  // 최대 시도 횟수
		int attempt = 0;

		do {
			tag = generateRandomTag();
			attempt++;

			if (attempt >= maxAttempts) {
				throw new BaseException(BaseResponseStatus.DUPLICATED_TAG);
			}
		} while (userProfileRepository.findByNicknameAndTag(userProfileNicknameDto.getNickname(), tag).isPresent());

		userProfileRepository.save(userProfileNicknameDto.toEntity(userProfile, tag));
	}

	@Override
	public UserProfileResponseDto readUserProfile(String userUuid) {
		return UserProfileResponseDto.toDto(userProfileRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int TAG_LENGTH = 5;  // 4자리로 바꾸려면 5를 4로 변경

	public static String generateRandomTag() {
		StringBuilder tag = new StringBuilder(TAG_LENGTH);
		Random random = new Random();

		for (int i = 0; i < TAG_LENGTH; i++) {
			int index = random.nextInt(CHAR_POOL.length());
			tag.append(CHAR_POOL.charAt(index));
		}

		return tag.toString();
	}

	private final KafkaTemplate<String, UserProfileKafkaVo> kafkaTemplate;

	@KafkaListener(topics = "comment-create", groupId = "comment-join-group", containerFactory = "commentEventListenerContainerFactory")
	public void consumeCommentEvent(CommentEventVo commentEventVo) {

		log.info("consumeCommentEvent: {}", commentEventVo);

		UserProfile userProfile = userProfileRepository.findByUserUuid(commentEventVo.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		sendMessage("comment-create-join-userprofile", UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	@KafkaListener(topics = "feed-create", groupId = "feed-join-group", containerFactory = "feedEventListenerContainerFactory")
	public void consumeFeedEvent(FeedEventVo feedEventVo) {

		log.info("consumeFeedEvent: {}", feedEventVo);

		UserProfile userProfile = userProfileRepository.findByUserUuid(feedEventVo.getUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		sendMessage("feed-create-join-userprofile", UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	public void sendMessage(String topic, UserProfileKafkaVo userProfileKafkaVo) {
		kafkaTemplate.send(topic, userProfileKafkaVo);
	}
}
