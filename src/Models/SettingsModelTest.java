package Models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SettingsModelTest {
    BigDecimal a = new BigDecimal("50.0");
    BigDecimal b = new BigDecimal("0.5");
    SettingsModel settings1 = new SettingsModel();


    @Test
    void shouldPrepareInsertQuery() {
        settings1.setSettingsId(1);
        settings1.setDaysForReturn(21);
        settings1.setPenalty(a);
        settings1.setPenaltyForDayOfDelay(b);
        String query = "INSERT INTO Settings (days_for_return, penalty, penalty_for_day_of_delay) " +
                "VALUES (" + settings1.getDaysForReturn() + ", '" + settings1.getPenalty() + "', '" + settings1.getPenaltyForDayOfDelay() + "')";

        assertEquals(query, settings1.prepareInsertQuery());
    }

    @Test
    void shouldPrepareUpdateQuery() {
        settings1.setSettingsId(1);
        settings1.setDaysForReturn(21);
        settings1.setPenalty(a);
        settings1.setPenaltyForDayOfDelay(b);
        String query = "UPDATE Settings SET `days_for_return` = '" + settings1.getDaysForReturn() + "', `penalty` = '" + settings1.getPenalty()
                + "', `penalty_for_day_of_delay` = '" + settings1.getPenaltyForDayOfDelay()
                + "' WHERE `Settings`.`Settings_id` = " + settings1.getSettingsId() + "";

        assertEquals(query, settings1.prepareUpdateQuery());
    }

    @Test
    void shouldPrepareDeleteQuery() {
        settings1.setSettingsId(1);
        settings1.setDaysForReturn(21);
        settings1.setPenalty(a);
        settings1.setPenaltyForDayOfDelay(b);
        String query = "DELETE FROM Settings WHERE Settings_id = " + settings1.getSettingsId() + "";
        assertEquals(query, settings1.prepareDeleteQuery());
    }
}
