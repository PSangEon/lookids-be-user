package lookids.user.userprofile.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.user.userprofile.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	Optional<UserProfile> findByUserUuid(String uuid);

	Optional<UserProfile> findByNicknameAndTage(String nickname, String tage);

	Optional<UserProfile> findById(Long id);
}
