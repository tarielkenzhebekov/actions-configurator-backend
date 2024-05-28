package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.rule;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.stage.Stage;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "rules")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Rule extends BaseEntity {
    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "rules_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @Column(name = "min_sum", nullable = false)
    Double minSum;

    @Column(name = "max_sum", nullable = false)
    Double maxSum;

    @Column(name = "amount", nullable = false)
    Long amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id", referencedColumnName = "id", nullable = false)
    Stage stage;
}
