package ru.vita.service.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.vita.service.entity.UserExcursion;
import ru.vita.service.entity.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;

public interface UserExcursionRepository extends JpaRepository<UserExcursion, Long> {

    @Query("SELECT ue FROM UserExcursion ue " +
            "WHERE ue.paymentStatus = :paymentStatus")
    List<UserExcursion> findAllByPaymentStatus(PaymentStatus paymentStatus);

    @Query("SELECT ue FROM UserExcursion ue " +
            "WHERE ue.user.id = :userId")
    List<UserExcursion> findAllExcursionByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE UserExcursion ue SET ue.paymentStatus = 'CONFIRMED', ue.version = ue.version + 1 " +
            "WHERE ue.id = :id AND ue.paymentStatus != 'CONFIRMED' AND ue.version = :expectedVersion")
    int updateToConfirmedIfPending(Long id, Integer expectedVersion);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ue FROM UserExcursion ue WHERE ue.id = :id")
    Optional<UserExcursion> findByIdWithLock(Long id);

    @Query("SELECT ue FROM UserExcursion ue " +
            "WHERE ue.user.username = :username")
    List<UserExcursion> findAllExcursionByUsername(String username);
}
