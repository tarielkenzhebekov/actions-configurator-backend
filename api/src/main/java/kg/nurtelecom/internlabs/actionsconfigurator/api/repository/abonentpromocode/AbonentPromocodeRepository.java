package kg.nurtelecom.internlabs.actionsconfigurator.api.repository.abonentpromocode;

import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentpromocode.AbonentPromocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbonentPromocodeRepository extends JpaRepository<AbonentPromocode, Long> {
    @Query("SELECT abonentPromocode FROM AbonentPromocode abonentPromocode WHERE abonentPromocode.abonentPhoneNumber LIKE %:phoneNumber%")
    List<AbonentPromocode> findByPhoneNumber(String phoneNumber);

    List<AbonentPromocode> findAbonentPromocodesByPromocodeId(Long id);
}
