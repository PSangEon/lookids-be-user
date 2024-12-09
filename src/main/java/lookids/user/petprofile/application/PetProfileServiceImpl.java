package lookids.user.petprofile.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.common.exception.BaseException;
import lookids.user.petprofile.domain.PetProfile;
import lookids.user.petprofile.dto.in.PetProfileImgDto;
import lookids.user.petprofile.dto.in.PetProfileRequestDto;
import lookids.user.petprofile.dto.in.PetProfileUpdateDto;
import lookids.user.petprofile.dto.in.PetProfileWeightDto;
import lookids.user.petprofile.dto.out.PetProfileResponseDto;
import lookids.user.petprofile.infrastructure.PetProfileRepository;
import lookids.user.petprofile.vo.in.FeedKafkaVo;
import lookids.user.petprofile.vo.out.PetProfileKafkaVo;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetProfileServiceImpl implements PetProfileService {

	private final PetProfileRepository petProfileRepository;
	private final KafkaTemplate<String, PetProfileKafkaVo> kafkaTemplate;

	@Override
	public void createPetProfile(PetProfileRequestDto petProfileRequestDto) {
		petProfileRepository.save(petProfileRequestDto.toEntity());
	}

	@Value("${petprofile.update}")
	private String petProfileUpdateTopic;

	@Override
	public void updatePetProfile(PetProfileUpdateDto petProfileUpdateDto) {
		PetProfile petProfile = petProfileRepository.findByPetCode(petProfileUpdateDto.getPetCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		PetProfile newPet = petProfileRepository.save(petProfileUpdateDto.toEntity(petProfile));
		kafkaTemplate.send(petProfileUpdateTopic, PetProfileResponseDto.toDto(newPet).toKafkaVo());
	}

	@Override
	public void updatePetProfileImage(PetProfileImgDto petProfileImgDto) {
		PetProfile petProfile = petProfileRepository.findByUserUuidAndPetCode(petProfileImgDto.getUserUuid(),
			petProfileImgDto.getPetCode()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		petProfileRepository.save(petProfileImgDto.toEntity(petProfile));
	}

	@Override
	public void updatePetProfileWeight(PetProfileWeightDto petProfileWeightDto) {
		PetProfile petProfile = petProfileRepository.findByUserUuidAndPetCode(petProfileWeightDto.getUserUuid(),
			petProfileWeightDto.getPetCode()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		petProfileRepository.save(petProfileWeightDto.toEntity(petProfile));
	}

	@Override
	public void deletePetProfile(String petCode) {
		PetProfile petProfile = petProfileRepository.findByPetCode(petCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		petProfileRepository.deleteById(petProfile.getId());
	}

	@Override
	public PetProfileResponseDto readPetProfile(String petCode) {
		return PetProfileResponseDto.toDto(petProfileRepository.findByPetCode(petCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	@Override
	public List<PetProfileResponseDto> readPetProfileList(String userUuid) {
		List<PetProfile> petProfileList = petProfileRepository.findByUserUuid(userUuid);
		return petProfileList.stream().map(PetProfileResponseDto::toDto).toList();
	}

	@Override
	public List<PetProfileResponseDto> reedRandomPetProfile(String uuid, Integer limit) {
		List<PetProfile> petProfileList = petProfileRepository.findRandomEntitiesExcludingUuid(uuid, limit);
		return petProfileList.stream().map(PetProfileResponseDto::toDto).toList();
	}

	@Value("${petprofile.out}")
	private String petProfileTopic;

	@KafkaListener(topics = "${feed.petprofile}", groupId = "${group-id}", containerFactory = "feedEventListenerContainerFactory")
	public void consumeFeedEvent(FeedKafkaVo feedKafkaVo) {

		log.info("consumeFeedKafkaVo: {}", feedKafkaVo);

		PetProfile petProfile = petProfileRepository.findByPetCode(feedKafkaVo.getPetCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		kafkaTemplate.send(petProfileTopic, PetProfileResponseDto.toDto(petProfile).toKafkaVo());
	}
}
