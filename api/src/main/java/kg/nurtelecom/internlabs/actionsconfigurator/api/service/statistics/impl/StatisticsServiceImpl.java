package kg.nurtelecom.internlabs.actionsconfigurator.api.service.statistics.impl;

import kg.nurtelecom.internlabs.actionsconfigurator.api.repository.statistics.StatisticsRepository;
import kg.nurtelecom.internlabs.actionsconfigurator.api.service.statistics.StatisticsService;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics.StatisticsByActionResponse;
import kg.nurtelecom.internlabs.actionsconfigurator.common.dto.response.statistics.StatisticsOverallResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StatisticsServiceImpl implements StatisticsService {
    StatisticsRepository statisticsRepository;
    Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    public StatisticsOverallResponse getOverall() {
        return StatisticsOverallResponse.builder()
                .actionsAmount(getAmountOfActions())
                .activatedActionsAmount(getAmountOfActivatedActions())
                .totalIncome(getTotalIncome())
                .promocodesIncome(getTotalIncomePromocodes())
                .ticketsIncome(getTotalIncomeTickets())
                .promocodesAmount(getAmountOfPromocodes())
                .ticketsAmount(getAmountOfTickets())
                .promocodesSold(getTotalPromocodesSold())
                .ticketsSold(getTotalTicketsSold())
                .promocodesUsed(getTotalPromocodesUsed())
                .ticketsUsed(getTotalTicketsUsed())
                .build();
    }

    public List<StatisticsByActionResponse> getByAction(Long id, String type) {
        List<StatisticsByActionResponse> data = new ArrayList<>();
        List<Long> amountPerStage = getAmountPerStage(id);
        List<Long> soldPerStage = getSoldPerStage(id, type);
        List<Long> incomePerStage = getIncomePerStage(id, type);
        List<Long> usedPerStage = getUsedPerStage(id, type);

        for (int i = 0; i < amountPerStage.size(); i++) {
            data.add(
                    StatisticsByActionResponse.builder()
                            .amount(amountPerStage.get(i))
                            .sold(soldPerStage.get(i))
                            .income(incomePerStage.get(i))
                            .used(usedPerStage.get(i))
                            .build()
            );
        }
        return data;
    }

    private Long getAmountOfActions() {
        Long amount;
        try {
            amount = statisticsRepository.findAmountOfActions();
        } catch (DataAccessException dae) {
            amount = 0L;
            logger.error("Can't get amount of actions " + dae);
        }
        return amount;
    }

    private Long getAmountOfActivatedActions() {
        Long amount;
        try {
            amount = statisticsRepository.findAmountOfActivatedActions();
        } catch (DataAccessException dae) {
            amount = 0L;
            logger.error("Can't get amount of activated actions " + dae);
        }
        return amount;
    }

    private Long getTotalIncome() {
        long incomePromocodes = getTotalIncomePromocodes();
        long incomeTickets = getTotalIncomeTickets();
        return incomePromocodes + incomeTickets;
    }

    private Long getTotalIncomePromocodes() {
        Long income;
        try {
            income = statisticsRepository.findTotalIncomePromocodes();
        } catch (DataAccessException dae) {
            income = 0L;
            logger.error("Can't get total income from promocodes " + dae);
        }
        return income;
    }

    private Long getTotalIncomeTickets() {
        Long income;
        try {
            income = statisticsRepository.findTotalIncomeTickets();
        } catch (DataAccessException dae) {
            income = 0L;
            logger.error("Can't get total income from tickets " + dae);
        }
        return income;
    }

    private Long getAmountOfPromocodes() {
        return statisticsRepository.findAmountOfPromocodes();
    }

    private Long getAmountOfTickets() {
        return statisticsRepository.findAmountOfTickets();
    }

    private Long getTotalPromocodesSold() {
        Long total;
        try {
            total = statisticsRepository.findTotalPromocodesSold();
        } catch (DataAccessException dae) {
            total = 0L;
            logger.error("Can't get total amount of sold promocodes " + dae);
        }
        return total;
    }

    private Long getTotalTicketsSold() {
        Long total;
        try {
            total = statisticsRepository.findTotalTicketsSold();
        } catch (DataAccessException dae) {
            total = 0L;
            logger.error("Can't get total amount of sold tickets " + dae);
        }
        return total;
    }

    private Long getTotalPromocodesUsed() {
        Long total;
        try {
            total = statisticsRepository.findTotalPromocodesUsed();
        } catch (DataAccessException dae) {
            total = 0L;
            logger.error("Can't get total amount of promocodes used " + dae);
        }
        return total;
    }

    private Long getTotalTicketsUsed() {
        Long total;
        try {
            total = statisticsRepository.findTotalTicketsUsed();
        } catch (DataAccessException dae) {
            total = 0L;
            logger.error("Can't get total amount of tickets used " + dae);
        }
        return total;
    }

    private List<Long> getAmountPerStage(Long id) {
        List<Long> total;
        try {
            total = statisticsRepository.findAmountPerStage(id);
        } catch (DataAccessException dae) {
            total = new ArrayList<>();
            logger.error("Can't get amount of promocodes/tickets per stage of action by id=" + id + " " + dae);
        }
        return total;
    }

    private List<Long> getSoldPerStage(Long id, String type) {
        List<Long> total;
        if ("PROMOCODE".equals(type)) {
            try {
                total = statisticsRepository.findPromocodesSoldPerStage(id);
            } catch (DataAccessException dae) {
                total = new ArrayList<>();
                logger.error("Can't get amount of sold promocodes per stage of action by id=" + id + " " + dae);
            }
        } else {
            try {
                total = statisticsRepository.findTicketsSoldPerStage(id);
            } catch (DataAccessException dae) {
                total = new ArrayList<>();
                logger.error("Can't get amount of sold tickets per stage of action by id=" + id + " " + dae);
            }
        }
        return total;
    }

    private List<Long> getIncomePerStage(Long id, String type) {
        List<Long> total;
        if ("PROMOCODE".equals(type)) {
            try {
                total = statisticsRepository.findPromocodesIncomePerStage(id);
            } catch (DataAccessException dae) {
                total = new ArrayList<>();
                logger.error("Can't get income from promocodes per stage of action by id=" + id + " " + dae);
            }
        } else {
            try {
                total = statisticsRepository.findTicketsIncomePerStage(id);
            } catch (DataAccessException dae) {
                total = new ArrayList<>();
                logger.error("Can't get income from tickets per stage of action by id=" + id + " " + dae);
            }
        }
        return total;
    }

    private List<Long> getUsedPerStage(Long id, String type) {
        List<Long> total;
        if ("PROMOCODE".equals(type)) {
            try {
                total = statisticsRepository.findPromocodesUsedPerStage(id);
            } catch (DataAccessException dae) {
                total = new ArrayList<>();
                logger.error("Can't get used promocodes per stage of action by id=" + id + " " + dae);
            }
        } else {
            try {
                total = statisticsRepository.findTicketsUsedPerStage(id);
            } catch (DataAccessException dae) {
                total = new ArrayList<>();
                logger.error("Can't get used tickets per stage of action by id=" + id + " " + dae);
            }
        }
        return total;
    }
}
