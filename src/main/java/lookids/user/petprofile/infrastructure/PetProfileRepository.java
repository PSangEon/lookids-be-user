package lookids.user.petprofile.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.user.petprofile.domain.PetProfile;

public interface PetProfileRepository extends JpaRepository<PetProfile, Long> {

	Optional<PetProfile> findByPetUuid(String uuid);

	List<PetProfile> findByUserUuid(String uuid);

	Optional<PetProfile> findByUserUuidAndPetUuid(String userUuid, String petUuid);

	Optional<PetProfile> findById(Long id);
}
