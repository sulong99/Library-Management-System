package Models;

import java.math.BigDecimal;

/**
 * Klasa model dla tabeli Setttings (ustawienia) zawierająca gettery i seetery oraz zapytania do wklejania, usuwania i edycji danych w tabeli.
 */
public class SettingsModel {
    private int settingsId;
    private int daysForReturn;
    private BigDecimal penalty;
    private BigDecimal penaltyForDayOfDelay;

    public SettingsModel(int settingsId, int daysForReturn, BigDecimal penalty, BigDecimal penaltyForDayOfDelay) {
        this.settingsId = settingsId;
        this.daysForReturn = daysForReturn;
        this.penalty = penalty;
        this.penaltyForDayOfDelay = penaltyForDayOfDelay;
    }

    public SettingsModel(int daysForReturn, BigDecimal penalty, BigDecimal penaltyForDayOfDelay) {
        this.daysForReturn = daysForReturn;
        this.penalty = penalty;
        this.penaltyForDayOfDelay = penaltyForDayOfDelay;
    }

    public SettingsModel() {
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    public int getDaysForReturn() {
        return daysForReturn;
    }

    public void setDaysForReturn(int daysForReturn) {
        this.daysForReturn = daysForReturn;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
    }

    public BigDecimal getPenaltyForDayOfDelay() {
        return penaltyForDayOfDelay;
    }

    public void setPenaltyForDayOfDelay(BigDecimal penaltyForDayOfDelay) {
        this.penaltyForDayOfDelay = penaltyForDayOfDelay;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat aktualizacji danych ustawienia.
     * @return zapytanie dotyczące aktualuzacji danych ustawienia o podanych parametrach po ID.
     */
    public String prepareUpdateQuery() {
        String query = "UPDATE Settings SET `days_for_return` = '" + this.daysForReturn + "', `penalty` = '" + this.penalty + "', `penalty_for_day_of_delay` = '" + this.penaltyForDayOfDelay + "' WHERE `Settings`.`Settings_id` = " + this.settingsId + "";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat usuwania danego ustawienia.
     * @return zapytanie dotyczące usuwaniu ustawienia o danym ID.
     */
    public String prepareDeleteQuery() {
        String query = "DELETE FROM Settings WHERE Settings_id = " + this.settingsId + "";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat dodawania nowych ustawień.
     * @return zapytanie dotyczące dodawania ustawnienia o podanych parametrach.
     */
    public String prepareInsertQuery() {
        String query = "INSERT INTO Settings (days_for_return, penalty, penalty_for_day_of_delay) " +
                "VALUES (" + this.daysForReturn + ", '" + this.penalty + "', '" + this.penaltyForDayOfDelay + "')";

        return query;
    }
}
