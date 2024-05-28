package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.CommonConstants;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule.Rule;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity
@Table(name = "promocodes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Promocode extends BaseEntity {

    public static final int PHONE_NUMBER_LENGTH = CommonConstants.PHONE_NUMBER_LENGTH;

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "promocodes_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @Column(name ="expired_date", nullable = false)
    LocalDateTime expiredDate;

    @ManyToOne
    @JoinColumn(name = "rule_id", referencedColumnName = "id", nullable = false)
    Rule rule;
}
