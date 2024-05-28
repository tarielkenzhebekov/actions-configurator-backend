package kg.nurtelecom.internlabs.actionsconfigurator.common.entity.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_sequence_generator")
    @GenericGenerator(
            name = "report_sequence_generator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "reports_seq"),
                    @Parameter(name = "initial_value", value = "1000")
            })
    @Column(name = "id")
    Long id;

    @JsonIgnore
    @Column(name = "path", nullable = false, length = 2048)
    String path;

    @Column(name = "name", nullable = false, length = 1024)
    String name;

    @Column(name = "date_created", updatable = false, nullable = false)
    LocalDateTime dateCreated;
}


