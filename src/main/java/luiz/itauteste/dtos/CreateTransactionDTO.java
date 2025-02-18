package luiz.itauteste.dtos;

import java.time.ZonedDateTime;

public record CreateTransactionDTO(double value, ZonedDateTime time) {
}
