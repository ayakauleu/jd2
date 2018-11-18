package database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import database.model.Currency;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentFilterDto {
    private Currency currency;
    private String paymentType;
    private LocalDate dateFrom;
    private LocalDate dateTill;

    private Integer limit;
    private Integer offset;
}
