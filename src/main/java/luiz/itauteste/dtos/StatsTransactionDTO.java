package luiz.itauteste.dtos;

public record StatsTransactionDTO(int count, double sum, double avg,
                                  double min, double max) {
}
