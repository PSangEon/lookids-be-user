package lookids.profile.petprofile.application;

import java.util.List;

import lookids.profile.petprofile.dto.in.PetProfileImgDto;
import lookids.profile.petprofile.dto.in.PetProfileRequestDto;
import lookids.profile.petprofile.dto.in.PetProfileUpdateDto;
import lookids.profile.petprofile.dto.in.PetProfileWeightDto;
import lookids.profile.petprofile.dto.out.PetProfileResponseDto;

public interface PetProfileService {
	void createPetProfile(PetProfileRequestDto petProfileRequestDto);

	void updatePetProfile(PetProfileUpdateDto petProfileUpdateDto);

	void updatePetProfileImage(PetProfileImgDto petProfileImgDto);

	void updatePetProfileWeight(PetProfileWeightDto petProfileWeightDto);

	void deletePetProfile(String petUuid);

	PetProfileResponseDto readPetProfile(String petUuid);

	List<PetProfileResponseDto> readPetProfileList(String userUuid);
}
