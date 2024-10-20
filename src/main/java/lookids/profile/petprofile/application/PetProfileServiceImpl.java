package lookids.profile.petprofile.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.profile.common.entity.BaseResponseStatus;
import lookids.profile.common.exception.BaseException;
import lookids.profile.petprofile.domain.PetProfile;
import lookids.profile.petprofile.dto.in.PetProfileImgDto;
import lookids.profile.petprofile.dto.in.PetProfileRequestDto;
import lookids.profile.petprofile.dto.in.PetProfileUpdateDto;
import lookids.profile.petprofile.dto.in.PetProfileWeightDto;
import lookids.profile.petprofile.dto.out.PetProfileResponseDto;
import lookids.profile.petprofile.infrastructure.PetProfileRepository;

@Service
@AllArgsConstructor
@Slf4j
public class PetProfileServiceImpl implements PetProfileService {

	private final PetProfileRepository petProfileRepository;

	@Override
	public void createPetProfile(PetProfileRequestDto petProfileRequestDto) {
		petProfileRepository.save(petProfileRequestDto.toEntity());
	}

	@Override
	public void updatePetProfile(PetProfileUpdateDto petProfileUpdateDto) {
		PetProfile petProfile = petProfileRepository.findByPetUuid(petProfileUpdateDto.getPetUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		petProfile.update(petProfileUpdateDto);
		petProfileRepository.save(petProfile);
	}

	@Override
	public void updatePetProfileImage(PetProfileImgDto petProfileImgDto) {
		PetProfile petProfile = petProfileRepository.findByUserUuidAndPetUuid(petProfileImgDto.getUserUuid(),
			petProfileImgDto.getPetUuid()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		petProfile.updateImage(petProfileImgDto.getImage());
		petProfileRepository.save(petProfile);
	}

	@Override
	public void updatePetProfileWeight(PetProfileWeightDto petProfileWeightDto) {
		PetProfile petProfile = petProfileRepository.findByUserUuidAndPetUuid(petProfileWeightDto.getUserUuid(),
			petProfileWeightDto.getPetUuid()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		petProfile.updateWeight(petProfileWeightDto.getWeight());
		petProfileRepository.save(petProfile);
	}

	@Override
	public void deletePetProfile(String petUuid) {
		PetProfile petProfile = petProfileRepository.findByPetUuid(petUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		petProfileRepository.deleteById(petProfile.getId());
	}

	@Override
	public PetProfileResponseDto readPetProfile(String petUuid) {
		return PetProfileResponseDto.toDto(petProfileRepository.findByPetUuid(petUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	@Override
	public List<PetProfileResponseDto> readPetProfileList(String userUuid) {
		List<PetProfile> petProfileList = petProfileRepository.findByUserUuid(userUuid);
		return petProfileList.stream().map(PetProfileResponseDto::toDto).toList();
	}
}
