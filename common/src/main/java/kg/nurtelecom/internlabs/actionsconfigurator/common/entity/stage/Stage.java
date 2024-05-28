package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action.Action;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Entity
@Table(name = "stages")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Stage extends BaseEntity implements Comparable<Stage> {

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "stages_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @Column(name ="start_date", nullable = false)
    LocalDateTime startDate;

    @Column(name ="end_date", nullable = false)
    LocalDateTime endDate;

    @Column(name = "ticket_limit")
    Integer ticketLimit;

    @Column(name = "promocode_limit")
    Integer promocodeLimit;

    @Column(name = "activated", nullable = false)
    @Builder.Default
    Boolean activated = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "action_id", referencedColumnName = "id", nullable = false)
    Action action;

    /**
     * Compares this object with the specified object for order. Returns a negative, zero, or positive integer
     * as this object less to, equal to, or greater then. Comparison is made by start dates.
     * @param other the object to be compared.
     * @return negative, zero, or positive as this object less to, equal to, or greater than.
     */
    @Override
    public int compareTo(@NonNull Stage other) {
        if (other.getEndDate().isEqual(startDate)
                || other.getEndDate().isBefore(startDate)) {
            return -1;
        } else if (other.getStartDate().isEqual(endDate)
                || other.getStartDate().isAfter(endDate)) {
            return 1;
        }
        return 0;
    }
}
