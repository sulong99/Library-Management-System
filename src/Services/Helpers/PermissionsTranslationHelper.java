package Services.Helpers;

import Models.PermissionEnum;

public class PermissionsTranslationHelper {

    /**
     * Metoda odpowiadająca za przetłumaczenie uprawniń na język polski
     * @param permission
     * @return
     */
    public static String translatePermission(String permission) {
        switch (permission) {
            case "User":
                return "Czytelnik";
            case "Worker":
                return "Pracownik";
            case "Administrator":
                return "Administrator";
        }
        return null;
    }
    /**
     * Metoda odpowiadająca za przetłumaczenie uprawniń na język angielski
     * @param permission
     * @return
     */
    public static PermissionEnum translatePermissionToEn(String permission) {
        switch (permission) {
            case "Użytkownik":
                return PermissionEnum.User;
            case "Pracownik":
                return PermissionEnum.Worker;
            case "Administrator":
                return PermissionEnum.Administrator;
        }
        return null;
    }
}
