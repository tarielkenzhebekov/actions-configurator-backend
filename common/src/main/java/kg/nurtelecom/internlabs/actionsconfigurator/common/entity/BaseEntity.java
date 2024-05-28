package kg.nurtelecom.internlabs.actionsconfigurator.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import kg.nurtelecom.internlabs.actionsconfigurator.common.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class BaseEntity {

    /**
     * The author user of this action.
     */
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @Builder.Default
    User createdBy = null;


    /**
     * User that updated this action.
     */
    @ManyToOne
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @Builder.Default
    User updatedBy = null;

    /**
     * Action creation date. Initialized inside service on creation.
     */
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;

    /**
     * Action update date. This field is null on creation. After creation,
     * everytime when entity changes (field was updated, action deleted,
     * stopped etc.) this property should be updated.
     */
    @Column(name = "updated_at")
    @Builder.Default
    LocalDateTime updatedAt = null;

    /**
     * Action deleted status. On deleting action do not erased from database
     * completely instead, this value changed to <code>true</code>. By default,
     * this property set to <code>false</code>.
     */
    @Column(name = "deleted", nullable = false)
    @Builder.Default
    Boolean deleted = false;

}
