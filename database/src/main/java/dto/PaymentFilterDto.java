package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Currency;
import model.PaymentType;

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
