package hellojpa.domain;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() {
    }

    public Period(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
