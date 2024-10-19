package lookids.profile.uwerprofile.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.profile.uwerprofile.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	Optional<UserProfile> findByUserUuid(String uuid);

	Optional<UserProfile> findById(Long id);
}
