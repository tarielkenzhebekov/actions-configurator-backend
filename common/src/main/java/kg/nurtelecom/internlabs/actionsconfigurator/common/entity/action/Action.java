package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.action;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.CommonConstants;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.promocode.Promocode;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.ActionType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

/**
 * Entity class for action. Uses JPA/Hibernate to create entity
 * in database.
 */
@Entity
@Table(name = "actions")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class Action extends BaseEntity {

    /**
     * Constant length values for description property.
     */
    public static final int DESCRIPTION_LENGTH = CommonConstants.DESCRIPTION_LENGTH;

    /**
     * Constant length value for name property.
     */
    public static final int NAME_LENGTH = CommonConstants.ACTION_NAME_LENGTH;

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "actions_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    /**
     * Action entity name.
     */
    @Column(name = "name", length = NAME_LENGTH, nullable = false)
    String name;

    /**
     * Action entity description.
     */
    @Column(name = "description", length = DESCRIPTION_LENGTH)
    String description;

    /**
     * Action starting date. Indicates when action should start.
     */
    @Column(name = "start_date", nullable = false)
    LocalDateTime startDate;

    /**
     * Action ending date. Indicates when action should end.
     */
    @Column(name = "end_date", nullable = false)
    LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    ActionType type;

    /**
     * Action activated status. On activating starts job that will deactivate this
     * action if
     */
    @Column(name = "activated", nullable = false)
    @Builder.Default
    Boolean activated = false;

    // TODO: Delete this templates.
    /**
     * Ticket template for this action. Either ticket or promocode template
     * should be present at one item.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    Ticket ticketTemplate;

    /**
     * Promocode template for this action. Either ticket or promocode template
     * should be present at one item.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocode_id", referencedColumnName = "id")
    Promocode promocodeTemplate;

}
