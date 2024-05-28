package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.abonentticket;

import jakarta.persistence.*;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.BaseEntity;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.CommonConstants;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.ticket.Ticket;
import kg.nurtelecom.internlabs.actionsconfigurator.common.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "abonent_tickets")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class AbonentTicket extends BaseEntity {

    public static final int ABONENT_PHONE_NUMBER_LENGTH = CommonConstants.PHONE_NUMBER_LENGTH;

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @GenericGenerator(
            name = "sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "abonent_tickets_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @Column(name = "abonent_phone_number", length = ABONENT_PHONE_NUMBER_LENGTH, nullable = false)
    String abonentPhoneNumber;

    @Column(name = "sum")
    Integer sum;

    @Column(name = "cashback")
    Integer cashback;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    Ticket ticket;
}
