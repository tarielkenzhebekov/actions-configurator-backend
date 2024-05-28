package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentpromocode;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.CommonConstants;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity
@Table(name = "abonent_promocodes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class AbonentPromocode extends BaseEntity {

    public static final int ABONENT_PHONE_NUMBER_LENGTH = CommonConstants.PHONE_NUMBER_LENGTH;
    public static final int PROMOCODE_LENGTH = CommonConstants.PROMOCODE_LENGTH;

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "abonent_promocodes_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @Column(name = "abonent_phone_number", length = ABONENT_PHONE_NUMBER_LENGTH, nullable = false)
    String abonentPhoneNumber;

    @Column(name = "code", length = PROMOCODE_LENGTH, unique = true, nullable = false)
    String code;

    @Column(name = "used_at")
    LocalDateTime usedAt;

    @Column(name = "sum")
    Integer sum;

    @Column(name = "cashback")
    Integer cashback;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @ManyToOne
    @JoinColumn(name = "promocode_id", referencedColumnName = "id")
    Promocode promocode;
}
